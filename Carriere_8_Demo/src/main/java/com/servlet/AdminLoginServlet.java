package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String password = req.getParameter("password");
			HttpSession session = req.getSession();

			if ("admin".equals(password)) {
				session.setAttribute("isAdmin", "true");
				session.setAttribute("name", "Admin");
				resp.sendRedirect("adminIndex.jsp");
			} else {
				session.setAttribute("adminLoginMessage", "Bad Credentials.");
				resp.sendRedirect("adminLogin.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
