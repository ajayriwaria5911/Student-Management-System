package com.example.backend.controller;

import com.example.backend.model.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

     @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping
    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student(1, "John", "Mca"));
        students.add(new Student(2, "Tushar", "Bca"));
        students.add(new Student(3, "Aman", "Bca"));
        students.add(new Student(4, "Rahul", "Mca"));
        students.add(new Student(5, "Ajay", "Mca"));

        return students;
    }

    // New API for BCA students
    @GetMapping("/bca")
    public List<Student> getBcaStudents() {

        return getStudents()
                .stream()
                .filter(student -> "Bca".equals(student.getCourse()))
                .collect(Collectors.toList());
    }

    // New API for names only
    @GetMapping("/names")
    public List<String> getNames() {

        return getStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/count")
    public int countStudents() {

        String sql = "SELECT COUNT(*) FROM students";

        return jdbcTemplate.queryForObject(
                sql,
                Integer.class
        );
    }
}