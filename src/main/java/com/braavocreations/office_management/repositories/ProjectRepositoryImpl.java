package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Project;
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
public class ProjectRepositoryImpl implements ProjectRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM project_table";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM project_table WHERE pro_id = ? ";
    private static final String SQL_CREATE = "INSERT INTO project_table (pro_id, pro_name, pro_supervisor_name, pro_date_added, pro_emp_id, pro_cont_id, pro_suggestor_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE project_table SET pro_name = ?, pro_supervisor_name = ?, pro_date_added = ?, pro_emp_id = ?, pro_cont_id = ?, pro_suggestor_name = ? " +
            "WHERE pro_id = ?";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM project_table WHERE pro_id = ?";


    @Override
    public List<Project> findAllI() throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, projectRowMapper);
    }

    @Override
    public Project findById(Integer pro_id) throws ResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{pro_id},projectRowMapper);
        }catch (Exception e){
            System.out.println(e);
            throw new ResourceNotFoundException("Project not found");
        }
    }

    @Override
    public Project Create( String pro_name, String pro_supervisor, long pro_added_date, Integer pro_empId, Integer pro_contractId, String pro_suggestor) throws ResourceNotFoundException {
        List<Project> contArray = findAllI();
        Integer proId;
        if (contArray.size() != 0){
            Project proObj = contArray.get(contArray.size()-1);
            proId = proObj.getPro_id() +1;
        }else {
            proId = 1;
        }
        Integer intDate = Math.toIntExact(pro_added_date);

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,proId);
                ps.setString(2,pro_name);
                ps.setString(3,pro_supervisor);
                ps.setInt(4,intDate);
                ps.setInt(5,pro_empId);
                ps.setInt(6,pro_contractId);
                ps.setString(7,pro_suggestor);


                return ps;

            }, keyHolder);

            return findById(proId);

        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public Project Update(Integer pro_id, String pro_name, String pro_supervisor, long pro_added_date, Integer pro_empId, Integer pro_contractId, String pro_suggestor) throws ResourceNotFoundException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{pro_name,pro_supervisor, pro_added_date, pro_empId,pro_contractId,pro_suggestor,pro_id});
            return findById(pro_id);
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer pro_id) throws ResourceNotFoundException {

        try{
            jdbcTemplate.update(SQL_DELETE_DEPARTMENT, new Object[]{pro_id});
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }


    }

    private RowMapper<Project> projectRowMapper = ((rs, rowNum) -> {
        return new Project(rs.getInt("pro_id"),
                rs.getString("pro_name"),
                rs.getString("pro_supervisor_name"),
                rs.getInt("pro_date_added"),
                rs.getInt("pro_emp_id"),
                rs.getInt("pro_cont_id"),
                rs.getString("pro_suggestor_name")

        );
    });
}
