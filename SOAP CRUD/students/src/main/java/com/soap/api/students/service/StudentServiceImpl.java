package com.soap.api.students.service;

import com.soap.api.students.dto.StudentDetailsRequest;
import com.soap.api.students.model.StudentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.soap.api.students.repos.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentDetails createStudent(StudentDetailsRequest request) {
        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setName(request.getName());
        studentDetails.setMarks(request.getMarks());
        return studentRepository.save(studentDetails);
    }

    @Override
    public StudentDetails getStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public void deleteStudentById(StudentDetails studentDetails) {
        studentRepository.delete(studentDetails);
    }

    @Override
    public StudentDetails UpdateStudentById(StudentDetails studentDetails) {
        return studentRepository.save(studentDetails);
    }
}
