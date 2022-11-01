package dev.yudin.exploring_doubles.Stubs;

import dev.yudin.exploring_doubles.dummy_object.Student;

import java.sql.SQLException;

public class StudentServiceImpl implements StudentService {

	private final StudentDAO studentDAO;

	public StudentServiceImpl(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public CreateStudentResponse create(String name, String studentOfClass) {
		CreateStudentResponse response;
		try {
			String roleNum = studentDAO.create(name, studentOfClass);

			response = new CreateStudentResponse(null, new Student(roleNum, name));
		} catch (SQLException e) {
			response = new CreateStudentResponse("SQLException" + e.getMessage(), null);
		} catch (Exception e) {
			response = new CreateStudentResponse(e.getMessage(), null);
		}
		return response;
	}
}
