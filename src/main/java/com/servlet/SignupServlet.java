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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		int experience = 0;
		if (req.getParameter("experience") != null && req.getParameter("experience") != "") {
			experience = Integer.parseInt(req.getParameter("experience"));
		}

		boolean notificationStatus = false;
		if (req.getParameter("notificationStatus") != null)
			notificationStatus = true;

		User u = new User(fullname, email, password, experience, notificationStatus);

		UserDAO dao = new UserDAO(DBConnect.getConn());
		
		if( notificationStatus ) {
			dao.addUserToEmailNotificationList(email);
		}

		HttpSession session = req.getSession();

		int f = dao.addUser(u);
		
		if (f == 200) {
			com.mail.Mailer.verifyAccount(email, u.getCode());
			resp.sendRedirect("indexMailSent.jsp");
		} else if (f == 500) {
			session.setAttribute("signupMessage", "Something wrong with server.");
			resp.sendRedirect("indexSignup.jsp");
		} else if (f == 400) {
			session.setAttribute("signupMessage", "Email already associated with an account.");
			resp.sendRedirect("indexSignup.jsp");
			return;
		}
	}

}
