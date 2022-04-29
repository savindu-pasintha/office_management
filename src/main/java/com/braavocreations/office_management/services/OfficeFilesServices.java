package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.domain.OfficeFiles;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface OfficeFilesServices {

    List<OfficeFiles> fetchAllOfficeFiles();

    OfficeFiles fetchOfficeFileById(Integer fileId) throws ResourceNotFoundException;

    OfficeFiles addOfficeFile( String file_name, String file_title, String file_sender,long fileCreatedDate) throws BadRequestException;

    OfficeFiles updateOfficeFile(Integer file_id, String file_name, String file_title, String file_sender,long fileCreatedDate) throws BadRequestException;

    void removeOfficeFile(Integer fileId) throws ResourceNotFoundException;
}
