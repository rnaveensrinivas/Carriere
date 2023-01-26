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
import com.entity.Job;

@WebServlet("/editJob")
public class EditJobServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		boolean pushNotification = false;
		if (req.getParameter("notificationStatus") != null)
			pushNotification = true;

		Job j = new Job();
		j.setId(Integer.parseInt(req.getParameter("id")));
		j.setCode(Integer.parseInt(req.getParameter("code")));
		j.setPay(req.getParameter("pay"));
		j.setRole(req.getParameter("role"));
		j.setLocation(req.getParameter("location"));
		j.setDateOfInterview(req.getParameter("dateOfInterview"));
		j.setDescription(req.getParameter("description"));
		j.setJobStatus(req.getParameter("jobStatus").equals("Active") ? 1 : 0);

		HttpSession session = req.getSession();
		JobDAO dao = new JobDAO(DBConnect.getConn());
		String s = dao.toString();
		boolean f = dao.editJob(j);
		if( f == true ) {
			resp.sendRedirect("adminViewListing.jsp");
		}else {
			session.setAttribute("editJobMessage", s + "Something wrong with server.");
			resp.sendRedirect("adminEditJob.jsp");
		}
	}

}
