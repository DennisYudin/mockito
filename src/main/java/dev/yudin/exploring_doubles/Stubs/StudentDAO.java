package dev.yudin.exploring_doubles.Stubs;

import java.sql.SQLException;

public interface StudentDAO {

	String create(String name, String className) throws SQLException;
}
