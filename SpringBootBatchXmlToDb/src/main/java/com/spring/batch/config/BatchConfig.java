package com.spring.batch.config;

import javax.sql.DataSource;

import com.spring.batch.reader.XMLFileReader;
import com.spring.batch.writer.DataBaseWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.model.Person;
import com.spring.batch.processor.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PersonItemProcessor processor(){
		return new PersonItemProcessor();
	}

	@Bean
	public XMLFileReader xmlReader()
	{
		return new XMLFileReader();
	}

	@Bean
	public DataBaseWriter dbWriter()
	{
		return new DataBaseWriter(dataSource);
	}

	//configuring the step for the job and pass the method inside the job
	@Bean
	public Step step1(){
		return stepBuilderFactory.get("step1")
				.<Person,Person>chunk(100).
				reader(xmlReader())
				.processor(processor())
				.writer(dbWriter()).build();
	}

	//configuring the job to start which reads xml and writes it to the database
	@Bean
	public Job exportPerosnJob(){
		return jobBuilderFactory.get("importPersonJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end().
				build();
	}
}
