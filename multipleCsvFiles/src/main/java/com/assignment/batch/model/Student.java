package com.assignment.batch.model;

import lombok.*;

import java.time.LocalDateTime;

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
	private LocalDateTime date;

	public static String[] getFieldNames(){
		return new String[]{"id","name","marks","grade","percentage","reason"};
	}
}
