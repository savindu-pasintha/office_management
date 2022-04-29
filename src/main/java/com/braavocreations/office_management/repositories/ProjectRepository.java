package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Project;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ProjectRepository {

    List<Project> findAllI() throws ResourceNotFoundException;
    Project findById(Integer pro_id) throws ResourceNotFoundException;
    Project Create( String pro_name, String pro_supervisor, long pro_added_date,Integer pro_empId, Integer pro_contractId, String pro_suggestor) throws ResourceNotFoundException;
    Project Update(Integer pro_id, String pro_name, String pro_supervisor, long pro_added_date,Integer pro_empId, Integer pro_contractId, String pro_suggestor) throws ResourceNotFoundException;
    void removeById(Integer pro_id) throws ResourceNotFoundException;
}
