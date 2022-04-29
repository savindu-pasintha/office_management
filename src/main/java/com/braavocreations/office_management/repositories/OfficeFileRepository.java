package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.OfficeFiles;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface OfficeFileRepository {

    List<OfficeFiles> findAll() throws ResourceNotFoundException;
    OfficeFiles findById(Integer file_id) throws ResourceNotFoundException;
    OfficeFiles Create( String file_name, String file_title, String file_sender,long fileCreatedDate) throws ResourceNotFoundException;
    OfficeFiles Update(Integer file_id, String file_name, String file_title, String file_sender,long fileCreatedDate) throws ResourceNotFoundException;
    void removeById(Integer file_id) throws ResourceNotFoundException;
}
