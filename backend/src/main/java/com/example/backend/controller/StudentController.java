package com.example.backend.controller;

import com.example.backend.model.Student;
import com.example.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentService service;

    // Get all students from database
    @GetMapping
    public List<Student> getStudents() {
        return service.getAllStudents();
    }

    // Get only BCA students
    @GetMapping("/bca")
    public List<Student> getBcaStudents() {

        return service.getAllStudents()
                .stream()
                .filter(student -> "Bca".equalsIgnoreCase(student.getCourse()))
                .collect(Collectors.toList());
    }

    // Get only names
    @GetMapping("/names")
    public List<String> getNames() {

        return service.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    // Count students in database
    @GetMapping("/count")
    public int countStudents() {

        String sql = "SELECT COUNT(*) FROM students";

        return jdbcTemplate.queryForObject(
                sql,
                Integer.class
        );
    }

    // // Test service
    // @GetMapping("/message")
    // public String getMessage() {
    //     return service.getMessage();
    // }
    @PostMapping
public Student addStudent(@RequestBody Student student) {
    return service.saveStudent(student);
}
    
}