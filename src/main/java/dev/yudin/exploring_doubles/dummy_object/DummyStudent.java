package dev.yudin.exploring_doubles.dummy_object;

public class DummyStudent extends Student {

	public DummyStudent(String roleNumber, String name) {
		super(null, null);
	}

	public String getRoleNumber() {
		throw new RuntimeException("Dummy student");
	}
	public String getName() {
		throw new RuntimeException("Dummy student");
	}
}
