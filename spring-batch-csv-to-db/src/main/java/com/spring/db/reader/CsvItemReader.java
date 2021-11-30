package com.spring.db.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import com.spring.db.model.Student;

public class CsvItemReader extends FlatFileItemReader<Student>{

	public CsvItemReader()
	{
	        this.setResource(new FileSystemResource("src/main/resources/users.csv"));
	        this.setLinesToSkip(1);

	        DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();
	        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

	        lineTokenizer.setDelimiter(",");
	        lineTokenizer.setNames("id", "name", "dept", "salary","marks");

	        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	        fieldSetMapper.setTargetType(Student.class);

	        defaultLineMapper.setLineTokenizer(lineTokenizer);
	        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
	        this.setLineMapper(defaultLineMapper);
	         
	}
}
