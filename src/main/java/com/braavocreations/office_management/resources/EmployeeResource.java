package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Employee;
import com.braavocreations.office_management.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

//http:localhost:8080//api/employee/1
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(HttpServletRequest request,
                                                        @PathVariable("employeeId") Integer employeeId) {

        Employee employee = employeeServices.fetchEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }



//Login page acces

    /**
     * Flow
     * 1: front ened eken en data tik all gannow
     * 2: eken ek eml/pass ek database eke thinwd blnaw
     * 3. thinw nm ek harid bll mokk hri ok nm ok ok nattn nh kiyl apu pront end ekt yawano
     * @param request
     * @param employeeMap
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Employee> loginEmployee(HttpServletRequest request,
                                                  @RequestBody Map<String, Object> employeeMap){
        //step 1
        Integer empId = Integer.parseInt((String) employeeMap.get("emp_Id"));
        String empEmail = (String) employeeMap.get("emp_email");
        String empPassword = (String) employeeMap.get("emp_password");
        // databse eka access krno. it passe data row ekk thinw nm ek ehemm pass krno front end ekt
        Employee employee = employeeServices.fetchEmployeeById(empId);// eken en data read krl return krnw
        //checking
        System.out.println(employee.toString());
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> employeeMap) {

        String empFirstName = (String) employeeMap.get("emp_first_name");
        String empLastName = (String) employeeMap.get("emp_last_name");
        String empEmail = (String) employeeMap.get("emp_email");
        String empPassword = (String) employeeMap.get("emp_password");
        String empAddress = (String) employeeMap.get("emp_address");
        Integer empBirthday =  Integer.parseInt((String)employeeMap.get("employee_birthday"));
        String empDesignation = (String) employeeMap.get("employee_designation");
        Integer empDepId = (Integer) employeeMap.get("employee_dept_id");


        Employee employee = employeeServices.addEmployee(empFirstName,empLastName,empEmail,empPassword,empAddress,empBirthday,empDesignation,empDepId);
        System.out.println(employee.toString());
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
