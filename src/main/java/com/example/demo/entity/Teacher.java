package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    @SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String email;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="department_id", referencedColumnName = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();


    public Teacher() {
    }

    public Teacher(Long id,
                   String name,
                   String email,
                   Long department_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department.setId(department_id);

    }

    public Teacher(String name,
                   String email,Long department_id) {
        this.name = name;
        this.email = email;
        this.department.setId(department_id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department_id='" + department.getId() + '\'' +
                '}';
    }

    public void assignDepartment(Department department) {

        this.department=department;
    }
}
