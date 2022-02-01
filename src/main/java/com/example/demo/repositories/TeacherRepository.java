package com.example.demo.repositories;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
        @Query("SELECT t FROM Teacher t Where t.email =?1")
        Optional<Teacher> findTeacherByEmail(String email);
        //this transforms to sql: SELECT * FROM student Where email =?
    }

