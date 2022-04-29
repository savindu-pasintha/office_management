package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Sector;
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
public class SectorRepositoryImpl implements SectorRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM sector_table";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM sector_table WHERE sector_id = ? ";
    private static final String SQL_CREATE = "INSERT INTO sector_table (sector_id, sector_name) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE sector_table SET sector_name = ?" +
            "WHERE sector_id = ?";
    private static final String SQL_DELETE_SECTOR = "DELETE FROM sector_table WHERE sector_id = ?";

    @Override
    public List<Sector> findAll() throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, sectorRowMapper);
    }

    @Override
    public Sector findById(Integer sector_id) throws ResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{sector_id},sectorRowMapper);
        }catch (Exception e){
            throw new ResourceNotFoundException("Department not found");
        }
    }

    @Override
    public Sector Create(String sector_name) throws BadRequestException {
        List<Sector> contArray = findAll();
        Integer sector_id;
        if (contArray.size() != 0){
            Sector SectorObj = contArray.get(contArray.size()-1);
            sector_id = SectorObj.getSector_id() +1;
        }else {
            sector_id = 1;
        }

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,sector_id);
                ps.setString(2,sector_name);
                return ps;

            }, keyHolder);
//            Integer createdSecId = (Integer) keyHolder.getKeys().get("sector_id");
            return findById(sector_id);

        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public Sector Update(Integer sector_Id, String sector_name) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{sector_name,sector_Id});
            return findById(sector_Id);
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer sector_Id) throws ResourceNotFoundException {
        try{
            jdbcTemplate.update(SQL_DELETE_SECTOR, new Object[]{sector_Id});
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }

    }

    private RowMapper<Sector> sectorRowMapper = ((rs, rowNum) -> {
        return new Sector(rs.getInt("sector_id"),
                rs.getString("sector_name")

        );
    });
}
