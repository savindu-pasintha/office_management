package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ContractServices {

    List<Contract> fetchAllContracts();

    Contract fetchContractById(Integer contId) throws ResourceNotFoundException;

    Contract addContract(String cont_name,long cont_date_handoever) throws BadRequestException;

    Contract updateContract(Integer cont_id, String cont_name,long cont_date_handoever) throws BadRequestException;

    void removeContract(Integer contId) throws ResourceNotFoundException;
}
