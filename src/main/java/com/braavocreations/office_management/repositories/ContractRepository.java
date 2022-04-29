package com.braavocreations.office_management.repositories;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ContractRepository {

    List<Contract> findAll() throws ResourceNotFoundException;
    Contract findById(Integer cont_id) throws ResourceNotFoundException;
    Contract create(String cont_name,long cont_date_handoever) throws ResourceNotFoundException;
    Contract update(Integer cont_id, String cont_name,long cont_date_handoever) throws ResourceNotFoundException;
    void removeById(Integer cont_id)throws ResourceNotFoundException;

}
