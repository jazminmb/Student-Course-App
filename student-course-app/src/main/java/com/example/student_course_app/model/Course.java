package com.example.student_course_app.model;

public class Course {
	private String courseNumber;
	private String grade;
	private int creditHours;
	
	public String getCourseNumber() {
		return courseNumber;
	}
	
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public int getCreditHours() {
		return creditHours;
	}
	
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
}
