package com.scheideggergroup.students.controller;

import com.scheideggergroup.students.exception.StudentNotFoundException;
import com.scheideggergroup.students.model.Student;
import com.scheideggergroup.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // Get All Students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Create a new Student
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student Student) {
        return studentRepository.save(Student);
    }

    // Get a Single Student
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long StudentId) throws StudentNotFoundException {
        return studentRepository.findById(StudentId)
                .orElseThrow(() -> new StudentNotFoundException(StudentId));
    }

    // Update a Student
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable(value = "id") Long StudentId,
                           @Valid @RequestBody Student StudentDetails) throws StudentNotFoundException {

        Student Student = studentRepository.findById(StudentId)
                .orElseThrow(() -> new StudentNotFoundException(StudentId));

        Student.setName(StudentDetails.getName());
        Student.setBirthDate(StudentDetails.getBirthDate());

        Student updatedStudent = studentRepository.save(Student);

        return updatedStudent;
    }

    // Delete a Student
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long StudentId) throws StudentNotFoundException {
        Student Student = studentRepository.findById(StudentId)
                .orElseThrow(() -> new StudentNotFoundException(StudentId));

        studentRepository.delete(Student);

        return ResponseEntity.ok().build();
    }
}