package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String email = req.getParameter("email");
			String password = req.getParameter("password");

			HttpSession session = req.getSession();

			UserDAO dao = new UserDAO(DBConnect.getConn());
			User user = dao.login(email, password);

			if (user != null) {
				if (user.getActivationStatus() == 0) {
					session.setAttribute("loginMessage", email+" email is not verfied. To verify, please check inbox.");
					resp.sendRedirect("indexLogin.jsp");
					return;
				}
				session.setAttribute("isUser", "true");
				session.setAttribute("fullname", user.getFullname());
				session.setAttribute("email", user.getEmail());
				session.setAttribute("experience", user.getExperience());
				resp.sendRedirect("userIndex.jsp");

			} else {
				session.setAttribute("loginMessage", "Bad Credentials.");
				resp.sendRedirect("indexLogin.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
