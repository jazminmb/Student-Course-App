package com.example.student_course_app.controller;

import com.example.student_course_app.model.Course;
import com.example.student_course_app.model.Student;
import com.example.student_course_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<Student> getAllStudents() throws IOException {
		return studentService.fetchStudents();
	}
	
	@GetMapping("/searchByName")
	public List<Course> searchByStudentName(@RequestParam String firstName) throws IOException {
		List<Student> students = studentService.fetchStudents();
		return students.stream()
				.filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
				.flatMap(student -> student.getCourses().stream())
				.toList();
	}
	
	@GetMapping("/searchByCourse")
	public List<Student> searchByCourseNumber(@RequestParam String courseNumber) throws IOException {
		List<Student> students = studentService.fetchStudents();
		return students.stream()
				.filter(student -> student.getCourses().stream()
						.anyMatch(course -> course.getCourseNumber().equalsIgnoreCase(courseNumber)))
				.toList();
	}
}
