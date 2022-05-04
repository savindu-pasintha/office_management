package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.OfficeFiles;
import com.braavocreations.office_management.services.OfficeFilesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/officeFile")
@CrossOrigin(origins = "*")
public class OfficeFilesResource {

    @Autowired
    OfficeFilesServices officeFilesServices;


    @GetMapping("")
    public ResponseEntity<List<OfficeFiles>> getAllFiles(HttpServletRequest request) {

        List<OfficeFiles> officeFiles = officeFilesServices.fetchAllOfficeFiles();
        return new ResponseEntity<>(officeFiles, HttpStatus.OK);
    }


    @GetMapping("/{fileId}")
    public ResponseEntity<OfficeFiles> getFileById(HttpServletRequest request,
                                                        @PathVariable("fileId") Integer fileId) {

        OfficeFiles officeFiles = officeFilesServices.fetchOfficeFileById(fileId);
        return new ResponseEntity<>(officeFiles, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<OfficeFiles> addFile(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> fileMap) {

        String fileName = (String) fileMap.get("file_name");
        String fileTitle = (String) fileMap.get("file_title");
        String fileSender = (String) fileMap.get("file_sender");
        Integer fileCreatedDate = (Integer) fileMap.get("file_created_date");
        OfficeFiles officeFiles = officeFilesServices.addOfficeFile(fileName,fileTitle,fileSender,fileCreatedDate);
        return new ResponseEntity<>(officeFiles, HttpStatus.CREATED);
    }


    @PostMapping("/update")
    public ResponseEntity<OfficeFiles> updateFile(HttpServletRequest request,
                                                       @RequestBody Map<String, Object> fileMap) {
        Integer fileId = (Integer) fileMap.get("file_id");
        String fileName = (String) fileMap.get("file_name");
        String fileTitle = (String) fileMap.get("file_title");
        String fileSender = (String) fileMap.get("file_sender");
        Integer fileCreatedDate = (Integer) fileMap.get("file_created_date");
        OfficeFiles officeFiles = officeFilesServices.updateOfficeFile(fileId,fileName,fileTitle,fileSender,fileCreatedDate);
        return new ResponseEntity<>(officeFiles, HttpStatus.CREATED);
    }


    @DeleteMapping("/{fileId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("fileId") Integer fileId) {

        officeFilesServices.removeOfficeFile(fileId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
