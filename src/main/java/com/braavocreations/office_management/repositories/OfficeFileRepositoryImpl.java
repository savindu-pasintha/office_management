package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.domain.OfficeFiles;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OfficeFileRepositoryImpl implements OfficeFileRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM file_table";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM file_table WHERE file_id = ? ";
    private static final String SQL_CREATE = "INSERT INTO file_table (file_id, file_name, file_title, file_sender,file_created_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE file_table SET file_name = ?, file_title = ?, file_sender = ?, file_created_date = ? " +
            "WHERE file_id = ?";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM file_table WHERE file_id = ?";



    private RowMapper<OfficeFiles> officeFilesRowMapper = ((rs, rowNum) -> {
        return new OfficeFiles(
                rs.getInt("file_id"),
                rs.getString("file_name"),
                rs.getString("file_title"),
                rs.getString("file_sender"),
                rs.getInt("file_created_date")
        );
    });

    @Override
    public List<OfficeFiles> findAll() throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, officeFilesRowMapper);
    }

    @Override
    public OfficeFiles findById(Integer file_id) throws ResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{file_id},officeFilesRowMapper);
        }catch (Exception e){
            throw new ResourceNotFoundException("File not found");
        }
    }

    @Override
    public OfficeFiles Create( String file_name, String file_title, String file_sender, long fileCreatedDate) throws ResourceNotFoundException {
        List<OfficeFiles> contArray = findAll();
        Integer fileId;
        if (contArray.size() != 0){
            OfficeFiles officeObj = contArray.get(contArray.size()-1);
            fileId = officeObj.getFile_id() +1;
        }else {
            fileId = 1;
        }
        Integer intDate = Math.toIntExact(fileCreatedDate);

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,fileId);
                ps.setString(2,file_name);
                ps.setString(3,file_title);
                ps.setString(4,file_sender);
                ps.setInt(5,intDate);
                return ps;

            }, keyHolder);

            return findById(fileId);

        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public OfficeFiles Update(Integer file_id, String file_name, String file_title, String file_sender, long fileCreatedDate) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{file_name,file_title,file_sender,fileCreatedDate,file_id});
            return findById(file_id);
        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer file_id) throws BadRequestException {

        try{
            jdbcTemplate.update(SQL_DELETE_DEPARTMENT, new Object[]{file_id});
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }

    }
}
