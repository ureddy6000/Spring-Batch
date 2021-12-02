package com.spring.batch.writer;

import com.spring.batch.model.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.ClassPathResource;

public class CSVFileItemWriter extends FlatFileItemWriter<Person> {

    public CSVFileItemWriter()
    {
        this.setResource(new ClassPathResource("persons.csv"));

        DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<Person>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<Person>();
        fieldExtractor.setNames(new String[]{"personId","firstName","lastName","email","age"});
        lineAggregator.setFieldExtractor(fieldExtractor);

        this.setLineAggregator(lineAggregator);
    }
}
