package com.braavocreations.office_management.services;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.exceptions.BadRequestException;
import com.braavocreations.office_management.exceptions.ResourceNotFoundException;
import com.braavocreations.office_management.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServicesImpl implements ContractServices {

    @Autowired
    ContractRepository contractRepository;


    @Override
    public List<Contract> fetchAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract fetchContractById(Integer contId) throws ResourceNotFoundException {
        return contractRepository.findById(contId);
    }

    @Override
    public Contract addContract( String cont_name, long cont_date_handoever) throws BadRequestException {
        return contractRepository.create(cont_name,cont_date_handoever);
    }

    @Override
    public Contract updateContract(Integer cont_id, String cont_name, long cont_date_handoever) throws BadRequestException {
        return contractRepository.update(cont_id,cont_name,cont_date_handoever);
    }

    @Override
    public void removeContract(Integer contId) throws ResourceNotFoundException {
        contractRepository.removeById(contId);

    }
}
