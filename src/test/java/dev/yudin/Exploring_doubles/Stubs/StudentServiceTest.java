package dev.yudin.Exploring_doubles.Stubs;

import static org.junit.jupiter.api.Assertions.assertFalse;

import dev.yudin.exploring_doubles.Stubs.CreateStudentResponse;
import dev.yudin.exploring_doubles.Stubs.StudentService;
import dev.yudin.exploring_doubles.Stubs.StudentServiceImpl;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
	private StudentService studentService;

	@Test
	void when_connection_times_out_then_the_student_is_not_saved() {
		studentService = new StudentServiceImpl(new ConnectionTimedOutStudentDAOStub());

		String classNine = "IX";
		String johnSmith = "john Smith";
		CreateStudentResponse resp = studentService.create(johnSmith, classNine);

		assertFalse(resp.isSuccess());
	}
}
