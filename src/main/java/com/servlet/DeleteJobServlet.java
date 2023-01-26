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

@WebServlet("/delete")
public class DeleteJobServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			
			JobDAO dao = new JobDAO(DBConnect.getConn());
			boolean f = dao.deleteJob(id);
	
			HttpSession session = req.getSession();

			if( f == true ) {
				session.setAttribute("msg", "Job Delete Successfully.");
			}else {
				session.setAttribute("msg", "Something wrong with server.");
			}
			
			resp.sendRedirect("adminViewListing.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
