package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import com.braavocreations.office_management.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServicesImpl implements DepartmentServices {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Integer depId) throws ResourceNotFoundException {
        return departmentRepository.findById(depId);
    }

    @Override
    public Department addDepartment( String dep_name, String dep_address, Integer dep_sector_id) throws BadRequestException {
        return departmentRepository.Create(dep_name,dep_address,dep_sector_id);
    }

    @Override
    public Department updateDepartment(Integer dep_id, String dep_name, String dep_address, Integer dep_sector_id) throws BadRequestException {
        return departmentRepository.update(dep_id,dep_name,dep_address,dep_sector_id);
    }

    @Override
    public void removeDepartment(Integer depId) throws ResourceNotFoundException {
        departmentRepository.removeById(depId);

    }
}
