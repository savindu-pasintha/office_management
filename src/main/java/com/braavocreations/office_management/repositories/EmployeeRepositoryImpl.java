package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Employee;
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
public class EmployeeRepositoryImpl implements EmployeeRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM employee_table";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM employee_table WHERE emp_id = ? ";
    private static final String SQL_CREATE = "INSERT INTO employee_table (emp_id, emp_first_name, emp_last_name, emp_email, emp_password, emp_address, emp_birthday, emp_designation, emp_dep_id) VALUES( ?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE employee_table SET emp_first_name = ?, emp_last_name = ?, emp_email = ?, emp_password = ?, emp_address = ?, emp_birthday = ?, emp_designation = ?, emp_dep_id = ?  " +
            "WHERE emp_id = ?";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee_table WHERE emp_id = ?";


    @Override
    public List<Employee> findAll() throws ResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{},employeeRowMapper);
    }

    @Override
    public Employee findById(Integer emp_id) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{emp_id},employeeRowMapper);
        }catch (Exception e){
            throw new ResourceNotFoundException("Employee not found");
        }
    }

    @Override
    public Employee Create(String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws BadRequestException {
        List<Employee> empArray = findAll();
        Integer emp_id;
        if (empArray.size() != 0){
            Employee emptObj = empArray.get(empArray.size()-1);
            emp_id = emptObj.getEmp_id() + 1;
        }else {
            emp_id = 1;
        }
        Integer intDOB = Math.toIntExact(empBirthday);

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,emp_id);
                ps.setString(2,empFirstName);
                ps.setString(3,empLastName);
                ps.setString(4,empEmal);
                ps.setString(5,empPassword);
                ps.setString(6,empAddress);
                ps.setInt(7,intDOB);
                ps.setString(8,empDesignation);
                ps.setInt(9,empDepId);
                return ps;

            }, keyHolder);

            return findById(emp_id);


        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }


    }

    @Override
    public Employee update(Integer emp_id, String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws BadRequestException {
        try {

            jdbcTemplate.update(SQL_UPDATE,new Object[]{empFirstName,empLastName,empEmal,empPassword,empAddress,empBirthday,empDesignation,empDepId,emp_id});
            return findById(emp_id);

        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer empId) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_DELETE_EMPLOYEE, new Object[]{empId});
        }catch (Exception e){
            throw new BadRequestException("Invalid Request");
        }
    }

    private RowMapper<Employee> employeeRowMapper = ((rs, rowNum) -> {
        return new Employee(
                rs.getInt("emp_id"),
                rs.getString("emp_first_name"),
                rs.getString("emp_last_name"),
                rs.getString("emp_email"),
                rs.getString("emp_password"),
                rs.getString("emp_address"),
                rs.getInt("emp_birthday"),
                rs.getString("emp_designation"),
                rs.getInt("emp_dep_id")
        );
    });
}
