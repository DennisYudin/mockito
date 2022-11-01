package dev.yudin.chapter_3.void_method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class DemoControllerTest {
	DemoController controller;
	@Mock
	LoginController loginController;

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	RequestDispatcher dispatcher;

	@BeforeEach
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
		controller = new DemoController(loginController);
	}

	@Test
	void when_subsystem_throws_exception_Then_routes_to_error_page_() throws Exception {

		when(request.getServletPath()).thenReturn("/logon.do");

		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);

		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

		controller.doGet(request, response);

		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);
	}
}