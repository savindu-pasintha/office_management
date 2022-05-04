package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Contract;
import com.braavocreations.office_management.services.ContractServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contract")
@CrossOrigin(origins = "*")
public class ContractResource {

    @Autowired
    ContractServices contractServices;

    @GetMapping("")
    public ResponseEntity<List<Contract>> getAllContracts(HttpServletRequest request) {

        List<Contract> contracts = contractServices.fetchAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<Contract> getContractById(HttpServletRequest request,
                                                    @PathVariable("contractId") Integer contractId) {

        Contract contract = contractServices.fetchContractById(contractId);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Contract> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> contractMap) {

        String contractName = (String) contractMap.get("contract_name");
        Integer contractHandOverDate = (Integer) contractMap.get("contract_handover_date");
        Contract contract = contractServices.addContract(contractName,contractHandOverDate);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Contract> updateContract(HttpServletRequest request,
                                                @RequestBody Map<String, Object> contractMap) {

        Integer contractId = (Integer) contractMap.get("contract_id");
        String contractName = (String) contractMap.get("contract_name");
        Integer contractHandOverDate = (Integer) contractMap.get("contract_handover_date");
        Contract contract = contractServices.updateContract(contractId,contractName,contractHandOverDate);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }



    @DeleteMapping("/{contractId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("contractId") Integer contractId) {

        contractServices.removeContract(contractId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
