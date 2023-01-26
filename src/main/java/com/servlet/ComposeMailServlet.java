package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.mail.Mailer;

@WebServlet("/composeMail")
public class ComposeMailServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String subject = req.getParameter("subject");
		String body = req.getParameter("body");
		int id = Integer.parseInt(req.getParameter("id"));

		HttpSession session = req.getSession();

		JobDAO dao = new JobDAO(DBConnect.getConn());
		ArrayList<String> emails = dao.getEnrolledEmails(id);

		boolean ret = Mailer.sendEmailGroup(body, subject, emails);

		if (ret == true) {
			session.setAttribute("composeMailMessage", "Mail sent.");
			resp.sendRedirect("adminComposeMail.jsp?id="+id);
		} else {
			session.setAttribute("composeMailMessage", "Mail sent.");
			resp.sendRedirect("adminComposeMail.jsp?id="+id);
		}

	}

}
