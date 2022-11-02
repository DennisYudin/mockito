package dev.yudin.chapter_3.void_method;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class LoginControllerTest {

	private @Mock HttpServletRequest request;
	private @Mock HttpServletResponse response;
	private @Mock LDAPManager ldapManager;

	@Mock
	HttpSession session;
	@Mock
	RequestDispatcher dispatcher;


	private LoginController controller;

	@BeforeEach
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
		controller = new LoginController(ldapManager);
	}

	@Test
	void when_valid_user_credentials_for_login_Then_routes_to_home_page() throws ServletException, IOException {
		when(request.getParameter(anyString())).thenReturn("user", "pwd");

		when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(true);

		when(request.getSession(true)).thenReturn(session);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

		controller.process(request, response);

		verify(request).getSession(true);
		verify(session).setAttribute(anyString(), anyString());
		verify(request).getRequestDispatcher(eq("home.jsp"));
		verify(dispatcher).forward(request, response);
	}

	@Test
	void when_invalid_user_credentials_Then_routes_to_login_page() throws ServletException, IOException {
		when(request.getParameter(anyString())).thenReturn("user", "pwd");
		when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(false);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

		controller.process(request, response);

		verify(request).getRequestDispatcher(eq("login.jsp"));
		verify(dispatcher).forward(request, response);
	}
}
