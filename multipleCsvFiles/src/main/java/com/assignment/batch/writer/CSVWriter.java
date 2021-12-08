package com.assignment.batch.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import com.assignment.batch.commons.Utils;
import com.assignment.batch.model.Student;
import com.assignment.batch.model.StudentDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVWriter implements ItemWriter<StudentDetails>{

	
	
	@Override
	public void write(List<? extends StudentDetails> items) throws Exception {
		List<Student> aboveFifty=new ArrayList<Student>();
		List<Student> belowFifty=new ArrayList<Student>();
		
		log.info("Students:", items);
		
		for(StudentDetails item : items)
		{
			for(Student student : item.getCurrentFileData())
			{
				if(student.getPercentage()>50)
				{
					
					aboveFifty.add(student);
					
				} else if(student.getPercentage()<50)
				{
					student.setReason("Percentage less than 50");
					belowFifty.add(student);
					
				}
			}
		}
		System.out.println(aboveFifty);
		System.out.println(belowFifty);
		
		FlatFileItemWriter<Student> successWriter=new FlatFileItemWriter<Student>();
		String file = "students_success" + "_" + Utils.createDateYYYYMMddHHMMSS() + ".csv";
        successWriter.setResource(new FileSystemResource(file));
        successWriter.setAppendAllowed(true);

        DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter("|");

        BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(Student.getFieldNames());

        lineAggregator.setFieldExtractor(fieldExtractor);
        successWriter.setLineAggregator(lineAggregator);
        successWriter.open(new ExecutionContext());
        
        successWriter.write(aboveFifty);
        successWriter.setEncoding("UTF-8");
        successWriter.close();
        
        FlatFileItemWriter<Student> rejectWriter=new FlatFileItemWriter<Student>();
		String rejectFile = "students_reject" + "_" + Utils.createDateYYYYMMddHHMMSS() + ".csv";
        rejectWriter.setResource(new FileSystemResource(rejectFile));
        rejectWriter.setAppendAllowed(true);

        DelimitedLineAggregator<Student> lineAggregatorReject = new DelimitedLineAggregator<>();
        lineAggregatorReject.setDelimiter("|");

        BeanWrapperFieldExtractor<Student> fieldExtractorReject = new BeanWrapperFieldExtractor<>();
        fieldExtractorReject.setNames(Student.getFieldNames());

        lineAggregatorReject.setFieldExtractor(fieldExtractorReject);
        rejectWriter.setLineAggregator(lineAggregatorReject);
        rejectWriter.open(new ExecutionContext());
        
        rejectWriter.write(belowFifty);
        rejectWriter.setEncoding("UTF-8");
        rejectWriter.close();
		
	}
   
}
