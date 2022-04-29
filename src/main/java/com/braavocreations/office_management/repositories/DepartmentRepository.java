package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll() throws ResourceNotFoundException;
    Department findById(Integer dep_id) throws ResourceNotFoundException;
    Department Create(String dep_name,String dep_address,Integer dep_sector_id) throws ResourceNotFoundException;
    Department update(Integer dep_id,String dep_name,String dep_address,Integer dep_sector_id) throws ResourceNotFoundException;
    void removeById(Integer dep_id) throws ResourceNotFoundException ;
}
