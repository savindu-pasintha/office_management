package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DepartmentServices {

    List<Department> fetchAllDepartments();

    Department fetchDepartmentById(Integer depId) throws ResourceNotFoundException;

    Department addDepartment(String dep_name,String dep_address,Integer dep_sector_id) throws BadRequestException;

    Department updateDepartment(Integer dep_id,String dep_name,String dep_address,Integer dep_sector_id) throws BadRequestException;

    void removeDepartment(Integer depId) throws ResourceNotFoundException;
}
