package com.procurement.system.service;

import com.procurement.system.entity.Category;
import com.procurement.system.entity.Department;
import com.procurement.system.repository.CategoryRepository;
import com.procurement.system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getCategoriesByDepartmentId(Long id) {
        return categoryRepository.findByDepartmentDepartmentId(id);
    }
}