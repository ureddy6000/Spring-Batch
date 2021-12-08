package com.assignment.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import com.assignment.batch.model.Student;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Student, Student>{
	private Set<Student> seenStudents = new HashSet<>();

	@Override
	public Student process(Student student) throws Exception {
		if(seenStudents.contains(student)) {
			return null;
		}
		seenStudents.add(student);
		if(student.getPercentage() == null){
			return null;
		}

		student.setDate(LocalDateTime.now());
		if(student.getPercentage() >= 50){
			student.setReason("Success");
			log.info("Success Student id: {}, name: {}, marks: {}, grade: {}, percentage: {}",
					student.getId(), student.getName(), student.getMarks(), student.getGrade(), student.getPercentage());
		}else {
			student.setReason("percentage less then 50");
			log.info("Rejected Student id: {}, name: {}, marks: {}, grade: {}, percentage: {}",
					student.getId(), student.getName(), student.getMarks(), student.getGrade(), student.getPercentage());
		}
		return student;
	}
}
