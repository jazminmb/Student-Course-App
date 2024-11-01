package com.example.student_course_app.service;

import com.example.student_course_app.model.Course;
import com.example.student_course_app.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public StudentService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}
	
	public List<Student> fetchStudents() throws IOException {
		String url = "https://hccs-advancejava.s3.amazonaws.com/student_course.json";
		String jsonData = restTemplate.getForObject(url, String.class);
		return objectMapper.readValue(jsonData, new TypeReference<List<Student>>() {});
	}
	
	public double calculateGPA(Student student) {
		double totalPoints = 0;
		int totalCredits = 0;
		for (Course course : student.getCourses()) {
			int points = convertGradeToPoints(course.getGrade());
			totalPoints += points * course.getCreditHours();
			totalCredits += course.getCreditHours();
		}
		return totalCredits > 0 ? totalPoints / totalCredits : 0;
	}
	
	private int convertGradeToPoints(String grade) {
		switch (grade.toUpperCase()) {
		case "A": return 4;
		case "B": return 3;
		case "C": return 2;
		case "D": return 1;
		default: return 0;
		}
	}
}
