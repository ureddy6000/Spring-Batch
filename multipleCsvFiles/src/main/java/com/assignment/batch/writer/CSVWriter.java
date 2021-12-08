package com.assignment.batch.writer;

import com.assignment.batch.model.Student;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import static com.assignment.batch.commons.Utils.createDateYYYYMMddHHMMSS;

public class CSVWriter extends FlatFileItemWriter<Student>{
    public CSVWriter(String fileName) {
        String file = fileName + "_" + createDateYYYYMMddHHMMSS() + ".csv";
        this.setResource(new FileSystemResource(file));
        this.setAppendAllowed(true);

        DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter("|");

        BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id","name","marks","grade","percentage","reason","date"});

        lineAggregator.setFieldExtractor(fieldExtractor);
        this.setLineAggregator(lineAggregator);
        this.open(new ExecutionContext());
    }
}
