package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Project;
import com.braavocreations.office_management.domain.Sector;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface SectorServices {

    List<Sector> fetchAllSectors();

    Sector fetchSectorById(Integer sectorId) throws ResourceNotFoundException;

    Sector addSector(String sector_name) throws BadRequestException;

    Sector updateSector(Integer sector_Id, String sector_name) throws BadRequestException;

    void removeSector(Integer sectorId) throws ResourceNotFoundException;
}
