package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v2/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService= teacherService;
    }
    @GetMapping
    public List<Teacher> getTeacher(){
        return teacherService.getTeacher();
    }

    @PostMapping
    public Teacher registerNewTeacher(@RequestBody Teacher teacher){
        return teacherService.addNewStudent(teacher);
    }

    @DeleteMapping(path = "{teacherId}")
    public boolean deleteTeacher(@PathVariable("teacherId") Long teacherId){
        return teacherService.deleteStudent(teacherId);
    }

    @PutMapping(path = "{teacherId}")
    public boolean updateTeacher(@PathVariable("teacherId") Long teacherId,
                              @RequestParam(required = false) String teacherName,
                              @RequestParam(required = false) String teacherEmail){

        return teacherService.updateTeacher(teacherId, teacherName, teacherEmail);
    }

    @PutMapping(path = "{teacherId}/department/{departmentId}")
    public Teacher assignDepartmentToTeachers(@PathVariable("teacherId") Long teacherId,
                              @PathVariable("departmentId") Long departmentId){

        return teacherService.assignDepartmentToTeachers(teacherId, departmentId);
    }
}
