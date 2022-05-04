package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeResource {

    @Autowired
    EmployeeServices employeeServices;


    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllEmployee(HttpServletRequest request) {

        List<Employee> employees = employeeServices.fetchAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(HttpServletRequest request,
                                                        @PathVariable("employeeId") Integer employeeId) {

        Employee employee = employeeServices.fetchEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> employeeMap) {

        String empFirstName = (String) employeeMap.get("emp_first_name");
        String empLastName = (String) employeeMap.get("emp_last_name");
        String empEmail = (String) employeeMap.get("emp_email");
        String empPassword = (String) employeeMap.get("emp_password");
        String empAddress = (String) employeeMap.get("emp_address");
        Integer empBirthday = (Integer) employeeMap.get("employee_birthday");
        String empDesignation = (String) employeeMap.get("employee_designation");
        Integer empDepId = (Integer) employeeMap.get("employee_dept_id");


        Employee employee = employeeServices.addEmployee(empFirstName,empLastName,empEmail,empPassword,empAddress,empBirthday,empDesignation,empDepId);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }


    @PostMapping("/update")
    public ResponseEntity<Employee> updateEmployee(HttpServletRequest request,
                                                @RequestBody Map<String, Object> employeeMap) {

        Integer empId = (Integer) employeeMap.get("employee_id");
        String empFirstName = (String) employeeMap.get("emp_first_name");
        String empLastName = (String) employeeMap.get("emp_last_name");
        String empEmail = (String) employeeMap.get("emp_email");
        String empPassword = (String) employeeMap.get("emp_password");
        String empAddress = (String) employeeMap.get("emp_address");
        Integer empBirthday = (Integer) employeeMap.get("employee_birthday");
        String empDesignation = (String) employeeMap.get("employee_designation");
        Integer empDepId = (Integer) employeeMap.get("employee_dept_id");


        Employee employee = employeeServices.updateEmployee(empId,empFirstName,empLastName,empEmail,empPassword,empAddress,empBirthday,empDesignation,empDepId);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("employeeId") Integer employeeId) {

        employeeServices.removeEmployee(employeeId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }




}
