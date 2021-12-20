package com.soap.api.students.service;

import com.soap.api.students.dto.StudentDetailsRequest;
import com.soap.api.students.model.StudentDetails;

public interface StudentService {
    StudentDetails createStudent(StudentDetailsRequest request);
    StudentDetails getStudentById(int id);
    void deleteStudentById(StudentDetails studentDetails);
    StudentDetails UpdateStudentById(StudentDetails studentDetails);
}
