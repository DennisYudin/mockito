package dev.yudin.chapter_3.void_method;

public class LDAPManagerImpl implements LDAPManager {

	@Override
	public boolean isValidUser(String userName, String encrypterPassword) {
		return false;
	}
}
