package com.assignment.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.assignment.batch.model.StudentDetails;
import com.assignment.batch.processor.PersonItemProcessor;
import com.assignment.batch.reader.CSVReader;
import com.assignment.batch.writer.CSVWriter;

@Configuration
public class BatchConfig {
	@Value("classpath:input/students*.csv")
	private Resource[] resource;

	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	

	public Step step1(){
		return stepBuilderFactory.get("step1")
				.<StudentDetails, StudentDetails>chunk(100)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public Job exportPersonJob(){
		return jobBuilderFactory.get("exportPeronJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end().build();
	}
	
	@Bean
	public CSVReader reader()
	{
		return new CSVReader();
	}
	
	@Bean
	public PersonItemProcessor processor()
	{
		return new PersonItemProcessor();
	}
	
	@Bean
	public CSVWriter writer()
	{
		return new CSVWriter();
	}
	
}
