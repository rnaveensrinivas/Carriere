package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.dao.UserDAO;
import com.entity.Job;
import com.mail.Mailer;

@WebServlet("/postJob")
public class PostJobServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String role = req.getParameter("role");
		String location = req.getParameter("location");
		String pay = req.getParameter("pay");
		String dateOfInterview = req.getParameter("dateOfInterview");
		String description = req.getParameter("description");
		int jobStatus = req.getParameter("jobStatus").equals("Active") ? 1 : 0;
		boolean pushNotification = false;
		if (req.getParameter("notificationStatus") != null)
			pushNotification = true;

		Job j = new Job(role, location, pay, dateOfInterview, description, jobStatus);

		System.out.println(j);

		HttpSession session = req.getSession();

		JobDAO dao = new JobDAO(DBConnect.getConn());

		if (pushNotification) {
			String message = "Role: " + role + "\nLocation: " + location
					+ "\nEnter job portal to know more.\nJob Portail: http://localhost:8534/Carriere/indexLogin.jsp\n\nRegards, Team.";
			Mailer.sendEmailGroup(message, "New Job Posted!",
					new UserDAO(DBConnect.getConn()).getPushNotificationList());
		}

		boolean f = dao.addJob(j);

		System.out.println("Boolean value of dao.addJob(j) : " + f);

		if (f == true) {
			resp.sendRedirect("adminViewListing.jsp");
		} else {
			session.setAttribute("postJobMessage", "Something wrong with server.");
			resp.sendRedirect("adminPostJob.jsp");
		}
	}
}
