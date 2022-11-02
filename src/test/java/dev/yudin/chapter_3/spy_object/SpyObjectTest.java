package dev.yudin.chapter_3.spy_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import dev.yudin.chapter_3.void_method.error.Error;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SpyObjectTest {

	@Test
	void when_spying_real_objects() throws Exception {

		Error error = new Error();
		error.setErrorCode("123");

		Error spyError = spy(error);

		//call real method from spy
		assertNotEquals("Q123", spyError.getErrorCode());

		//Changing value using spy
		spyError.setErrorCode(null);
		assertNull(spyError.getErrorCode());

		//Stubbing method
		when(spyError.getErrorCode()).thenReturn("E456");

		//Stubbed method value E456 is returned NOT NULL
		assertNotEquals(null, spyError.getErrorCode());

		//Stubbed method value E456
		assertEquals("E456", spyError.getErrorCode());
	}

	@Test
	void when_doReturn_fails() throws Exception {
		List<String> list = new ArrayList<>();
		List<String> spy = spy(list);

		//doReturn fixed the issue
		doReturn("now reachable").when(spy).get(0);

		assertEquals("now reachable", spy.get(0));
	}
}