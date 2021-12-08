package com.assignment.batch.reader;

import com.assignment.batch.model.Student;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.Resource;

public class CSVMultiReader extends MultiResourceItemReader<Student> {
    public CSVMultiReader(Resource[] resource) {
        this.setResources(resource);
        this.setDelegate(new CSVReader());
    }
}
