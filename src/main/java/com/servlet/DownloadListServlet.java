package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/downloadList")
public class DownloadListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			int id = Integer.parseInt(req.getParameter("id"));

			System.out.println("id = " + id );
			UserDAO dao = new UserDAO(DBConnect.getConn());

			List<User> list = dao.getUserEnrolledList(id);

//			PrintWriter pw = new PrintWriter(new File("C:\\Users\\MIRITPC\\Desktop\\csv\\books_table1.csv"));
			PrintWriter pw = new PrintWriter(new File("C:\\Users\\rnave\\Downloads\\list_id_"+id+".csv"));
			StringBuilder sb = new StringBuilder();

			pw.write("Full Name, Email, Experience\r\n");
			for (User u : list) {
				sb.append(u.getFullname());
				sb.append(",");
				sb.append(u.getEmail());
				sb.append(",");
				sb.append(u.getExperience());
				sb.append("\r\n");
			}

			pw.write(sb.toString());
			pw.close();

			HttpSession session = req.getSession();

			session.setAttribute("downloadEnrolledUserMessage", "Check Downloads Folder");
			resp.sendRedirect("adminViewEnrolled.jsp?id=" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
