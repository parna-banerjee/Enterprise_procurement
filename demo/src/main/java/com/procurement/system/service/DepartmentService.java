package com.procurement.system.service;

import com.procurement.system.entity.Category;
import com.procurement.system.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    List<Category> getCategoriesByDepartmentId(Long id);

}