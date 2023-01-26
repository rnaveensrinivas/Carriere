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

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {

	
	private static final long serialVersionUID = -5462078586296916336L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		
		boolean notificationStatus = false;
		if (req.getParameter("notificationStatus") != null)
			notificationStatus = true;


		UserDAO dao = new UserDAO(DBConnect.getConn());		

		int f = dao.setNotificationStatus(email, notificationStatus);
		
		resp.sendRedirect("userIndex.jsp");
		
	}
	
	

}
