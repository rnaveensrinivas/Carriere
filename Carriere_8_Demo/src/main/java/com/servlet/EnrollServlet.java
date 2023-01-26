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
import com.entity.User;

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int id = Integer.parseInt(req.getParameter("id"));

			JobDAO dao = new JobDAO(DBConnect.getConn());

			HttpSession session = req.getSession();

			User u = new User((String) session.getAttribute("fullname"), (String) session.getAttribute("email"),
					(Integer) session.getAttribute("experience"));
			
			boolean f = dao.addUserToJob(id, u);

			if (f == true) {
				session.setAttribute("enrollJobMessage", "Enrolled Succesfully.");
			} else {
				session.setAttribute("enrollJobMessage", "Something wrong.");
			}

			resp.sendRedirect("userViewJob.jsp?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
