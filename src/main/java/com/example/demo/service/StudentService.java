package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    private String message;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        log.info("Saving new student {} to database",student.getName());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        Student returnStudent= studentRepository.save(student);
        if(returnStudent!=null) {
             message=emailSenderService.sendSimpleEmail("ishahid.bese17seecs@seecs.edu.pk", "Your Account has been registered", "Springboot: Account Registered");
        }
        else {
            message="Email not sent";
        }

        return returnStudent;

    }

    public boolean deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        log.info("Deleting student {} to database",studentRepository.getById(studentId).getName());

        if(!exists){
            throw new IllegalStateException("student with id" + studentId + "does not exists");
        }
        studentRepository.deleteById(studentId);
        return true;
    }

    @Transactional
    public boolean updateStudent(Long studentId, String studentName, String studentEmail) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> {
                    throw new IllegalStateException("student with id" + studentId + "does not exists");
                });
        if (studentName != null &&
                studentName.length()>0 &&
                !Objects.equals(student.getName(),studentName)){
            student.setName(studentName);
        }
        if (studentEmail != null &&
                studentEmail.length()>0 &&
                !Objects.equals(student.getEmail(),studentEmail)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentEmail);

            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(studentEmail);

        }
        return true;
    }


}