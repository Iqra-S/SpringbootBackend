package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v2/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService= studentService;
    }
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public Student registerNewStudent(@RequestBody Student student){

        return studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public boolean deleteStudent(@PathVariable("studentId") Long studentId){
        return studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public boolean updateStudent(@PathVariable("studentId") Long studentId,
                                 @RequestParam(required = false) String studentName,
                                 @RequestParam(required = false) String studentEmail){

        return studentService.updateStudent(studentId, studentName, studentEmail);
    }
}
