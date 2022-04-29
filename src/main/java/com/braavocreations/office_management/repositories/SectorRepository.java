package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Sector;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface SectorRepository {
    List<Sector> findAll() throws ResourceNotFoundException;
    Sector findById(Integer sector_id) throws ResourceNotFoundException;
    Sector Create(String sector_name) throws ResourceNotFoundException;
    Sector Update(Integer sector_Id, String sector_name) throws ResourceNotFoundException;
    void removeById(Integer sector_Id) throws ResourceNotFoundException;

}
