package dev.yudin.chapter_3.void_method;


import dev.yudin.chapter_3.void_method.error.Error;
import dev.yudin.chapter_3.void_method.error.ErrorHandler;
import dev.yudin.chapter_3.void_method.error.MessageRepository;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DemoController")
public class DemoController extends HttpServlet {

	private LoginController loginController;

	private ErrorHandler errorHandler;
	private MessageRepository messageRepository;

	public DemoController(LoginController loginController) {
		this.loginController = loginController;
	}

	public DemoController() {
		loginController = new LoginController(new LDAPManagerImpl());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String urlContext = req.getServletPath();
			if (urlContext.equals("/")) {
				req.getRequestDispatcher("login.jsp").forward(req, res);
			} else if (urlContext.equals("/logon.do")) {
				loginController.process(req, res);
			} else {
				req.setAttribute("error", "Invalid request path '" + urlContext + "' ");
				req.getRequestDispatcher("error.jsp").forward(req, res);
			}
		} catch (Exception ex) {
			String errorMsg = ex.getMessage();

			Error errorDto = new Error();
			errorDto.setTrace(ex.getStackTrace());
//			errorHandler.mapTo(errorDto);

			if (errorDto.getErrorCode() != null) {
				String errorCode = errorDto.getErrorCode();
				errorMsg = messageRepository.lookUp(errorCode);
			}

			req.setAttribute("error", errorMsg);
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}
	}
}
