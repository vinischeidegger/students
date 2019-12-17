package com.scheideggergroup.students.exception;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(long studentId) {
        super(String.format("Student is not found with id : '%s'", studentId));
    }
}