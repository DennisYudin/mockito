package dev.yudin.chapter_3.void_method;


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

	public DemoController() {
		loginController = new LoginController(new LDAPManagerImpl());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String urlContext = req.getServletPath();
		if (urlContext.equals("/")) {
			req.getRequestDispatcher("login.jsp").forward(req, res);
		} else if (urlContext.equals("/logon.do")) {
			loginController.process(req, res);
		} else {
			req.setAttribute("error", "Invalid request path '" + urlContext + "' ");
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}
	}
}