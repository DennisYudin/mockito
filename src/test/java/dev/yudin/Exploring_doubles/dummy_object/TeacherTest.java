package dev.yudin.Exploring_doubles.dummy_object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.yudin.Exploring_Doubles.dummy_object.DummyStudent;
import dev.yudin.Exploring_Doubles.dummy_object.Grades;
import dev.yudin.Exploring_Doubles.dummy_object.Marks;
import dev.yudin.Exploring_Doubles.dummy_object.Teacher;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class TeacherTest {

	@Test
	 void when_marks_above_seventy_five_percent_returns_very_good() {
		DummyStudent dummyStudent = new DummyStudent("dennis", "yudin");

		Marks inEnglish = new Marks(dummyStudent, "English002", new BigDecimal("81.00"));
		Marks inMath = new Marks(dummyStudent, "Math005", new BigDecimal("97.00"));
		Marks inHistory = new Marks(dummyStudent, "History007", new BigDecimal("79.00"));

		List<Marks> listOfMarks = Arrays.asList(inHistory, inMath, inEnglish);

		Grades actual = new Teacher().calculatePercentageAndReturnGradeBy(listOfMarks);
		Grades expected = Grades.VeryGood;

		assertEquals(expected, actual);
	}
}
