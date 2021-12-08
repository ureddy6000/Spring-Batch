package com.assignment.batch.model;

import java.util.List;


public class StudentDetails {

	private List<Student> previousFileData;
	private List<Student> currentFileData;
	
	public List<Student> getPreviousFileData() {
		return previousFileData;
	}
	public void setPreviousFileData(List<Student> previousFileData) {
		this.previousFileData = previousFileData;
	}
	public List<Student> getCurrentFileData() {
		return currentFileData;
	}
	public void setCurrentFileData(List<Student> currentFileData) {
		this.currentFileData = currentFileData;
	}
	
	
	
}
