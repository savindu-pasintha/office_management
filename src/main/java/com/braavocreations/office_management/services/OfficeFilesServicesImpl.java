package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.OfficeFiles;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import com.braavocreations.office_management.repositories.OfficeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfficeFilesServicesImpl implements OfficeFilesServices {
    @Autowired
    OfficeFileRepository officeFileRepository;

    @Override
    public List<OfficeFiles> fetchAllOfficeFiles() {
        return officeFileRepository.findAll();
    }

    @Override
    public OfficeFiles fetchOfficeFileById(Integer fileId) throws ResourceNotFoundException {
        return officeFileRepository.findById(fileId);
    }

    @Override
    public OfficeFiles addOfficeFile(String file_name, String file_title, String file_sender, long fileCreatedDate) throws BadRequestException {
        return officeFileRepository.Create(file_name,file_title,file_sender,fileCreatedDate);
    }

    @Override
    public OfficeFiles updateOfficeFile(Integer file_id, String file_name, String file_title, String file_sender, long fileCreatedDate) throws BadRequestException {
        return officeFileRepository.Update(file_id,file_name,file_title,file_sender,fileCreatedDate);
    }

    @Override
    public void removeOfficeFile(Integer fileId) throws ResourceNotFoundException {
        officeFileRepository.removeById(fileId);

    }
}
