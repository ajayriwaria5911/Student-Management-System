package com.example.backend.service;

import com.example.backend.exception.StudentNotFoundException;
import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(
            StudentRepository repository) {

        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student saveStudent(
            Student student) {

        return repository.save(student);
    }

    public Student getStudentById(
            Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        ));
    }

    public String getMessage() {
        return "Student Service Working!";
    }
    public Student addStudent(StudentRequestDTO dto) {

    Student student = new Student();
    
    student.setName(dto.getName());
    student.setCourse(dto.getCourse());

    return repository.save(student);
}
public Student updateStudent(Integer id, StudentRequestDTO dto) {

    Student student = repository
	                    .findById(id)
	                    .orElseThrow( () -> new StudentNotFoundException(
                                            "Student Not Found"
                                    )
                    );

    student.setName(dto.getName());
    student.setCourse(dto.getCourse());
    
    return repository.save(student);
}
public String deleteStudent(Integer id) {
    repository.deleteById(id);
    return "Student Deleted";
}
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {

    return ResponseEntity.ok(
            service.deleteStudent(id)
    );

}
}