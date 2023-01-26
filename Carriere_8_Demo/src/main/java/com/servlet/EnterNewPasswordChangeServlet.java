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

@WebServlet("/enterNewPasswordChange")
public class EnterNewPasswordChangeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int otp = Integer.parseInt(req.getParameter("otp"));
			String password = req.getParameter("password");
			HttpSession session = req.getSession();

			if (session.getAttribute("changePasswordOTP") == null) {
				session.setAttribute("changePasswordMessage", "OTP Expired, try again.");
				resp.sendRedirect("indexEnterEmail.jsp");
			} else if (otp != (int) session.getAttribute("changePasswordOTP")) {
				System.out.println("ootp = " + (int) session.getAttribute("changePasswordOTP"));
				session.setAttribute("changePasswordMessage", "Bad OTP");
				resp.sendRedirect("indexChangePassword.jsp");
			} else {
				String email = (String) session.getAttribute("changePasswordEmail");
				UserDAO dao = new UserDAO(DBConnect.getConn());
				dao.changePassword(email, password);
				session.setAttribute("loginMessage", "Password Changed, try logging in.");
				session.removeAttribute("changePasswordOTP");
				session.removeAttribute("changePasswordEmail");
				resp.sendRedirect("indexLogin.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
