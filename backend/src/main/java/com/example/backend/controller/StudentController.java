package com.example.backend.controller;

import com.example.backend.dto.StudentRequestDTO;
import com.example.backend.dto.StudentResponseDTO;
import com.example.backend.model.Student;
import com.example.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Get all students
    @GetMapping
    public List<Student> getStudents() {
        return service.getAllStudents();
    }

    // Get BCA students only
    @GetMapping("/bca")
    public List<Student> getBcaStudents() {
        return service.getAllStudents()
                .stream()
                .filter(student -> "Bca".equalsIgnoreCase(student.getCourse()))
                .collect(Collectors.toList());
    }

    // Get names only
    @GetMapping("/names")
    public List<String> getNames() {
        return service.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    // Count students
    @GetMapping("/count")
    public int countStudents() {
        String sql = "SELECT COUNT(*) FROM students";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable Integer id) {
        Student student = service.getStudentById(id);

        StudentResponseDTO response = new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getCourse()
        );

        return ResponseEntity.ok(response);
    }

    // Add new student
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentRequestDTO dto) {
        Student student = service.addStudent(dto);
        return ResponseEntity.ok(student);
    }

    // Update existing student
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(service.updateStudent(id, dto));
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        return ResponseEntity.ok(service.deleteStudent(id));
    }
}