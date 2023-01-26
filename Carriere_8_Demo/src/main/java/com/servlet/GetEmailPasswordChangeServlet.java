package com.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.mail.Mailer;

@WebServlet("/enterEmail")
public class GetEmailPasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String email = req.getParameter("email");
			HttpSession session = req.getSession();

			UserDAO dao = new UserDAO(DBConnect.getConn());
			
			if(dao.checkUserExists(email)) {
				session.setAttribute("changePasswordEmail", email);
				int otp = new Random().nextInt(100_000, 999_999);
				session.setAttribute("changePasswordOTP", otp);
				String message = "Your one time OTP is " + otp;
				String subject = "Change Password OTP";
				Mailer.sendEmail( message, subject , email);
				resp.sendRedirect("indexChangePassword.jsp");
			}else {
				session.setAttribute("indexEmailMessage", "Bad email.");
				resp.sendRedirect("indexEnterEmail.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
