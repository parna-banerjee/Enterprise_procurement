package com.procurement.system.controller;

import com.procurement.system.entity.Category;
import com.procurement.system.entity.Department;
import com.procurement.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/{id}/categories")
    public List<Category> getCategoriesByDepartmentId(@PathVariable Long id) {
        return departmentService.getCategoriesByDepartmentId(id);
    }
}