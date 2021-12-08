package com.assignment.batch.processor;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.assignment.batch.model.Student;
import com.assignment.batch.model.StudentDetails;


public class PersonItemProcessor implements ItemProcessor<StudentDetails, StudentDetails>{
	
	@Override
	public StudentDetails process(StudentDetails studentDetails) throws Exception {
		
	List<Student> current=studentDetails.getCurrentFileData();
	List<Student> previous=studentDetails.getPreviousFileData();
	
	current.removeAll(previous);
	studentDetails.setCurrentFileData(current);
	
	return studentDetails;
	}
}
