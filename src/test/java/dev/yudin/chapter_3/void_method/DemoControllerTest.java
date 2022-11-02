package dev.yudin.chapter_3.void_method;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.yudin.chapter_3.void_method.error.Error;
import dev.yudin.chapter_3.void_method.error.ErrorHandler;
import dev.yudin.chapter_3.void_method.error.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

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

	@Mock
	ErrorHandler errorHandler;

	@Mock
	MessageRepository messageRepository;

	@BeforeEach
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
		controller = new DemoController(loginController);
	}

	@Test
	void when_subsystem_throws_exception_Then_routes_to_error_page_() throws Exception {

		when(request.getServletPath()).thenReturn("/logon.do");

		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);

		doAnswer((Answer<Object>) invocation -> {
					Error err = (Error) invocation.getArguments()[0];
					err.setErrorCode("123");
					return err;
				}
		).when(errorHandler).mapTo(isA(Error.class));

		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

		controller.doGet(request, response);

		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);

//		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//		verify(messageRepository).lookUp(captor.capture());
//		assertEquals("123", captor.getValue());
	}
}