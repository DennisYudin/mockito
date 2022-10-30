package dev.yudin.Exploring_Doubles.Spy;

import dev.yudin.Exploring_Doubles.dummy_object.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
	private Map<String, List<Student>> studentCourseMap = new HashMap<>();

	private StudentServiceSpy spy;

	public void setSpy(StudentServiceSpy spy) {
		this.spy = spy;
	}
	
	public void enrollToCourse(String courseName, Student student) {
		MethodInvocation invocation = new MethodInvocation();
		invocation
				.addParam(courseName)
				.addParam(student)
				.setMethod("enrollToCourse");
		spy.registerCall(invocation);

		List<Student> listOfStudents = studentCourseMap.get(courseName);
		if (IsNull(listOfStudents)) {
			listOfStudents = new ArrayList<>();
		}
		if (notSuchStudentInList(student, listOfStudents)) {
			listOfStudents.add(student);
		}
		studentCourseMap.put(courseName, listOfStudents);
	}

	private boolean IsNull(List<Student> listOfStudents) {
		return listOfStudents == null;
	}

	private boolean notSuchStudentInList(Student student, List<Student> listOfStudents) {
		return !listOfStudents.contains(student);
	}
}
