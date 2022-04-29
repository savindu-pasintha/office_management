package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Project;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import com.braavocreations.office_management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServicesImpl implements ProjectServices {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> fetchAllProjects() {
        return projectRepository.findAllI();
    }

    @Override
    public Project fetchProjectById(Integer proId) throws ResourceNotFoundException {
        return projectRepository.findById(proId);
    }

    @Override
    public Project addProject(String pro_name, String pro_supervisor, long pro_added_date, Integer pro_empId, Integer pro_contractId, String pro_suggestor) throws BadRequestException {
        return projectRepository.Create(pro_name,pro_supervisor,pro_added_date,pro_empId,pro_contractId,pro_suggestor);
    }

    @Override
    public Project updateProject(Integer pro_id, String pro_name, String pro_supervisor, long pro_added_date, Integer pro_empId, Integer pro_contractId, String pro_suggestore) throws BadRequestException {
        return projectRepository.Update(pro_id,pro_name,pro_supervisor,pro_added_date,pro_empId,pro_contractId,pro_suggestore);
    }

    @Override
    public void removeProject(Integer proId) throws ResourceNotFoundException {
        projectRepository.removeById(proId);

    }
}
