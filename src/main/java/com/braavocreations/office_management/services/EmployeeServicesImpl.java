package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import com.braavocreations.office_management.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServicesImpl implements EmployeeServices{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee fetchEmployeeById(Integer empId) throws ResourceNotFoundException {
        return employeeRepository.findById(empId);
    }

    @Override
    public Employee addEmployee(String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws BadRequestException {
        return employeeRepository.Create(empFirstName,empLastName,empEmal,empPassword,empAddress,empBirthday,empDesignation,empDepId);
    }

    @Override
    public Employee updateEmployee(Integer emp_id, String empFirstName, String empLastName, String empEmal, String empPassword, String empAddress, long empBirthday, String empDesignation, Integer empDepId) throws BadRequestException {
        return employeeRepository.update(emp_id,empFirstName,empLastName,empEmal,empPassword,empAddress,empBirthday,empDesignation,empDepId);
    }

    @Override
    public void removeEmployee(Integer empId) throws ResourceNotFoundException {
        employeeRepository.removeById(empId);

    }
}
