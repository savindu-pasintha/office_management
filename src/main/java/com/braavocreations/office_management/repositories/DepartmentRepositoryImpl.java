package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.domain.Department;
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
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM department_table";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM department_table WHERE dept_id = ? ";
    private static final String SQL_CREATE = "INSERT INTO department_table (dept_id, dept_name, dept_address, dept_sector_id) VALUES (?, ?, ?,?)";
    private static final String SQL_UPDATE = "UPDATE department_table SET dept_name = ?, dept_address = ?, dept_sector_id = ? " +
            "WHERE dept_id = ?";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM department_table WHERE dept_id = ?";


    @Override
    public List<Department> findAll() throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, departmentRowMapper);
    }

    @Override
    public Department findById(Integer dep_id) throws ResourceNotFoundException {
        try{
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{dep_id},departmentRowMapper);
        }catch (Exception e){
            throw new ResourceNotFoundException("Department not found");
        }

    }

    @Override
    public Department Create( String dep_name, String dep_address, Integer dep_sector_id) throws BadRequestException {
        List<Department> contArray = findAll();
        Integer dept_id;
        if (contArray.size() != 0){
            Department deptObj = contArray.get(contArray.size()-1);
            dept_id = deptObj.getDept_id() +1;
        }else {
            dept_id = 1;
        }

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,dept_id);
                ps.setString(2,dep_name);
                ps.setString(3,dep_address);
                ps.setInt(4,dep_sector_id);
                return ps;

            }, keyHolder);
//            Integer createdDepId = (Integer) keyHolder.getKeys().get("dept_id");
            return findById(dept_id);

        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public Department update(Integer dep_id, String dep_name, String dep_address, Integer dep_sector_id) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{dep_name,dep_address, dep_sector_id,dep_id});
            return findById(dep_id);
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer dep_id) throws BadRequestException {
        try{
            jdbcTemplate.update(SQL_DELETE_DEPARTMENT, new Object[]{dep_id});
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }
    }

    private RowMapper<Department> departmentRowMapper = ((rs, rowNum) -> {
        return new Department(rs.getInt("dept_id"),
                rs.getString("dept_name"),
                rs.getString("dept_address"),
                rs.getInt("dept_sector_id")
                );
    });
}
