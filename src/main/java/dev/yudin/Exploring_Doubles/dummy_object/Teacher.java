package dev.yudin.Exploring_Doubles.dummy_object;

import java.math.BigDecimal;
import java.util.List;

public class Teacher {

	public Grades calculatePercentageAndReturnGradeBy(List<Marks> marksList) {
		BigDecimal aggregate = BigDecimal.ZERO;

		for (Marks mark : marksList) {
			aggregate = aggregate.add(mark.getMarks());
		}

		BigDecimal percentage = calculate(aggregate, marksList.size());

		if (percentage.compareTo(new BigDecimal("90.00")) > 0) {
			return Grades.Excellent;
		}
		if (percentage.compareTo(new BigDecimal("75.00")) > 0) {
			return Grades.VeryGood;
		}
		if (percentage.compareTo(new BigDecimal("60.00")) > 0) {
			return Grades.Good;
		}
		if (percentage.compareTo(new BigDecimal("40.00")) > 0) {
			return Grades.Average;
		}
		return Grades.Poor;
	}

	private BigDecimal calculate(BigDecimal aggregate,
								 int numberOfSubjects) {
		BigDecimal percent = new BigDecimal(aggregate.doubleValue() / numberOfSubjects);
		return percent;
	}
}
