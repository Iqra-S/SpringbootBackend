package com.example.demo.service;


import com.example.demo.entity.Department;
import com.example.demo.repositories.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }

    public List<Department> getDepartment() {
        return departmentRepository.findAll();

    }

    public Department addNewDepartment(Department department) {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName(department.getName());
        if (departmentOptional.isPresent()){
            throw new IllegalStateException("name is taken");
        }
        return departmentRepository.save(department);

    }

    public Boolean deleteDepartment(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);

        if(!exists){
            throw new IllegalStateException("department with id" + departmentId + "does not exists");
        }
        departmentRepository.deleteById(departmentId);
        return true;
    }

    @Transactional
    public boolean updateDepartment(Long departmentId, String departmentName) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()-> {
                    throw new IllegalStateException("department with id" + departmentId + "does not exists");
                });
        if (departmentName != null &&
                departmentName.length()>0 &&
                !Objects.equals(department.getName(),departmentName)){
            department.setName(departmentName);
        }
        return true;
    }
}
