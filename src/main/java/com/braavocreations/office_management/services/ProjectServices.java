package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.OfficeFiles;
import com.braavocreations.office_management.domain.Project;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ProjectServices {

    List<Project> fetchAllProjects();

    Project fetchProjectById(Integer proId) throws ResourceNotFoundException;

    Project addProject( String pro_name, String pro_supervisor, long pro_added_date,Integer pro_empId, Integer pro_contractId, String pro_suggestor) throws BadRequestException;

    Project updateProject(Integer pro_id, String pro_name, String pro_supervisor, long pro_added_date,Integer pro_empId, Integer pro_contractId, String pro_suggestore) throws BadRequestException;

    void removeProject(Integer proId) throws ResourceNotFoundException;
}
