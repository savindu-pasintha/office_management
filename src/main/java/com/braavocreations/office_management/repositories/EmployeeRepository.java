package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll() throws ResourceNotFoundException;
    Employee findById(Integer emp_id) throws ResourceNotFoundException;
    Employee Create(String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws ResourceNotFoundException;
    Employee update(Integer emp_id,String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws ResourceNotFoundException;
    void removeById(Integer empId) throws ResourceNotFoundException;
}
