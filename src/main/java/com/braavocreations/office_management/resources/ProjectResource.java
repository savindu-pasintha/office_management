package com.braavocreations.office_management.resources;

import com.braavocreations.office_management.domain.Department;
import com.braavocreations.office_management.domain.Project;
import com.braavocreations.office_management.services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectResource {

    @Autowired
    ProjectServices projectServices;

    @GetMapping("")
    public ResponseEntity<List<Project>> getAllProjects(HttpServletRequest request) {

        List<Project> projects = projectServices.fetchAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(HttpServletRequest request,
                                                        @PathVariable("projectId") Integer projectId) {

        Project project = projectServices.fetchProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> projectMap) {

        String proName = (String) projectMap.get("project_name");
        String proSupervisot = (String) projectMap.get("project_supervisor");
        Integer projectAddedDate = (Integer) projectMap.get("project_added_date");
        Integer projectEmpId = (Integer) projectMap.get("project_emp_id");
        Integer projectContId = (Integer) projectMap.get("project_cont_id");
        String proSuggestor = (String) projectMap.get("project_suggestor");
        Project project = projectServices.addProject(proName,proSupervisot,projectAddedDate,projectEmpId,projectContId,proSuggestor);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Project> updateProject(HttpServletRequest request,
                                              @RequestBody Map<String, Object> projectMap) {

        Integer proId = (Integer) projectMap.get("project_id");
        String proName = (String) projectMap.get("project_name");
        String proSupervisot = (String) projectMap.get("project_supervisor");
        Integer projectAddedDate = (Integer) projectMap.get("project_added_date");
        Integer projectEmpId = (Integer) projectMap.get("project_emp_id");
        Integer projectContId = (Integer) projectMap.get("project_cont_id");
        String proSuggestor = (String) projectMap.get("project_suggestor");
        Project project = projectServices.updateProject(proId,proName,proSupervisot,projectAddedDate,projectEmpId,projectContId,proSuggestor);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Map<String, Boolean>> deleteProject(HttpServletRequest request,
                                                               @PathVariable("projectId") Integer projectId) {

        projectServices.removeProject(projectId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
