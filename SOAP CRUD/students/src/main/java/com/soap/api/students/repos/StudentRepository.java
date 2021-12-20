package com.soap.api.students.repos;

import com.soap.api.students.model.StudentDetails;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentDetails, Integer> {
    StudentDetails findById(int studentId);
}
