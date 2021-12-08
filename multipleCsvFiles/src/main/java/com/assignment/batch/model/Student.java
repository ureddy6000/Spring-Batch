package com.assignment.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private Integer id;
	private String name;
	private Integer marks;
	private String grade;
	private Integer percentage;
	private String reason;
	

	public static String[] getFieldNames(){
		return new String[]{"id","name","marks","grade","percentage","reason"};
	}
}
