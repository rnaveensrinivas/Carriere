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

@WebServlet("/unenroll")
public class UnerollServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int id = Integer.parseInt(req.getParameter("id"));

			JobDAO dao = new JobDAO(DBConnect.getConn());

			HttpSession session = req.getSession();

			boolean f = dao.removeUserFromJob(id, (String) session.getAttribute("email"));

			if (f == true) {
				session.setAttribute("enrollJobMessage", "Unenrolled Succesfully.");
			} else {
				session.setAttribute("enrollJobMessage", "Something wrong.");
			}

			resp.sendRedirect("userViewJob.jsp?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
