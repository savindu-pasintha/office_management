package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface EmployeeServices {

    List<Employee> fetchAllEmployees();

    Employee fetchEmployeeById(Integer empId) throws ResourceNotFoundException;

    Employee addEmployee(String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws BadRequestException;

    Employee updateEmployee(Integer emp_id,String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws BadRequestException;

    void removeEmployee(Integer empId) throws ResourceNotFoundException;
}
