package dev.yudin.Exploring_Doubles.Stubs;

import java.sql.SQLException;

public interface StudentDAO {

	String create(String name, String className) throws SQLException;
}
