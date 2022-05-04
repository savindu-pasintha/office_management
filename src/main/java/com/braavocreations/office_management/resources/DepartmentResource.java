package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "*")
public class DepartmentResource {

    @Autowired
    DepartmentServices departmentServices;


    @GetMapping("")
    public ResponseEntity<List<Department>> getAllDepartments(HttpServletRequest request) {

        List<Department> departments = departmentServices.fetchAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(HttpServletRequest request,
                                                    @PathVariable("departmentId") Integer departmentId) {

        Department department = departmentServices.fetchDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(HttpServletRequest request,
                                                @RequestBody Map<String, Object> departmentMap) {

        String deptName = (String) departmentMap.get("department_name");
        String deptAddress = (String) departmentMap.get("department_address");
        Integer deptSectorId = (Integer) departmentMap.get("department_sector_id");
        Department department = departmentServices.addDepartment(deptName,deptAddress,deptSectorId);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }


    @PostMapping("/update")
    public ResponseEntity<Department> updateDepartment(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> departmentMap) {
        Integer depId = (Integer) departmentMap.get("department_id");
        String deptName = (String) departmentMap.get("department_name");
        String deptAddress = (String) departmentMap.get("department_address");
        Integer deptSectorId = (Integer) departmentMap.get("department_sector_id");
        Department department = departmentServices.updateDepartment(depId,deptName,deptAddress,deptSectorId);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }



    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("departmentId") Integer departmentId) {

        departmentServices.removeDepartment(departmentId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }





}
