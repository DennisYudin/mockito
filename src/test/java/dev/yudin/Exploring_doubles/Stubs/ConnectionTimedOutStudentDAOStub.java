package dev.yudin.Exploring_doubles.Stubs;

import dev.yudin.exploring_doubles.Stubs.StudentDAO;

import java.sql.SQLException;

public class ConnectionTimedOutStudentDAOStub implements StudentDAO {
	@Override
	public String create(String name, String className) throws SQLException {
		throw new SQLException("DB connection timed out");
	}
}
