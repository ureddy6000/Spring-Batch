package com.assignment.batch.reader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.assignment.batch.model.Student;
import com.assignment.batch.model.StudentDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVReader implements ItemReader<StudentDetails>{

	StudentDetails studentDetails=new StudentDetails();
	
	private boolean isRead=false;
	
	@Override
	public StudentDetails read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if(isRead)
		{
			return null;
		}
	
   Stream<String> mappingStream=Files.lines(Paths.get("C:\\Users\\UDAYST\\Desktop\\New\\multipleCsvFiles\\src\\main\\resources\\input\\students_current.csv"),StandardCharsets.UTF_8 );
   List<String> currentList=mappingStream.collect(Collectors.toList());
   List<Student> studentCurrentList=new ArrayList<>();
    
   int counter=0;
   int counterTwo=0;
    
   for(String item : currentList)
   {
	   
	   counter++;
	   if(counter!=1)
	   {
		   log.info(item);
		   String[] currentArray=item.split("\\|");
		   Student student=new Student();
		   student.setId(Integer.parseInt(currentArray[0]));
		   student.setName(currentArray[1]);
		   student.setMarks(Integer.parseInt(currentArray[2]));
		   student.setGrade(currentArray[3]);
		   student.setPercentage(Integer.parseInt(currentArray[4]));
		   studentCurrentList.add(student);
		 
	   }
   }
   
   studentDetails.setCurrentFileData(studentCurrentList);
   
   mappingStream=Files.lines(Paths.get("C:\\Users\\UDAYST\\Desktop\\New\\multipleCsvFiles\\src\\main\\resources\\input\\students_previous.csv"),StandardCharsets.UTF_8 );
   List<String> previousList=mappingStream.collect(Collectors.toList());
   List<Student> studentPreviousList=new ArrayList<>();
   
   for(String item : previousList)
   {
	   
	   counterTwo++;
	   if(counterTwo!=1)
	   {
		   log.info(item);
		   String[] previousArray=item.split("\\|");
		   Student student=new Student();
		   student.setId(Integer.parseInt(previousArray[0]));
		   student.setId(Integer.parseInt(previousArray[0]));
		   student.setName(previousArray[1]);
		   student.setMarks(Integer.parseInt(previousArray[2]));
		   student.setGrade(previousArray[3]);
		   student.setPercentage(Integer.parseInt(previousArray[4]));
		   studentPreviousList.add(student);
		   
	   }
   }
    studentDetails.setPreviousFileData(studentPreviousList);
    isRead=true;
	return studentDetails;
	}
}
