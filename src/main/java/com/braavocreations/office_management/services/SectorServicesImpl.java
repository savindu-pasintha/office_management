package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Sector;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import com.braavocreations.office_management.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SectorServicesImpl implements SectorServices {

    @Autowired
    SectorRepository sectorRepository;

    @Override
    public List<Sector> fetchAllSectors() {
        return sectorRepository.findAll();
    }

    @Override
    public Sector fetchSectorById(Integer sectorId) throws ResourceNotFoundException {
        return sectorRepository.findById(sectorId);
    }

    @Override
    public Sector addSector(String sector_name) throws BadRequestException {
        return sectorRepository.Create(sector_name);
    }

    @Override
    public Sector updateSector(Integer sector_Id, String sector_name) throws BadRequestException {
        return sectorRepository.Update(sector_Id,sector_name);
    }

    @Override
    public void removeSector(Integer sectorId) throws ResourceNotFoundException {
        sectorRepository.removeById(sectorId);
    }
}
