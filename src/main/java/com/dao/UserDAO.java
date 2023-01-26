package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;

public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean checkUserExists(String email) {

		String sql = "select fullname from user where email=?";
		PreparedStatement p;
		try {
			p = conn.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void changePassword(String email, String password) {

		String sql = "update user set password = ? where email = ?";
		PreparedStatement p;
		try {
			p = conn.prepareStatement(sql);
			p.setString(1, password);
			p.setString(2, email);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUserToEmailNotificationList(String email) {

		String sql = "insert into notificationlist(email) values(?)";
		PreparedStatement p;
		try {
			p = conn.prepareStatement(sql);
			p.setString(1, email);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int addUser(User u) {

		try {
			if (checkUserExists(u.getEmail())) {
				System.out.println("Account exists.");
				return 400;
			} else {
				System.out.println("New Account.");
				String sql = "insert into user(fullname, email, password, experience, notificationStatus, activationStatus, code) values(?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, u.getFullname());
				ps.setString(2, u.getEmail());
				ps.setString(3, u.getPassword());
				ps.setInt(4, u.getExperience());
				ps.setInt(5, u.getNotificationStatus() ? 1 : 0);
				ps.setInt(6, 0);
				ps.setLong(7, u.getCode());

				if (ps.executeUpdate() == 1) {
					return 200;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Exception");
			e1.printStackTrace();
		}

		return 500;
	}

	public int verify(int code) {
		int ret = 0;
		try {

			String sql = "update user set activationStatus = 1 where code = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, code);

			ret = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public User login(String email, String password) {
		User u = null;

		try {

			String sql = "select * from user where email = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				u = new User();
				u.setFullname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setActivationStatus(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;

	}

	public boolean getNotificationStatus(String email) {

		try {

			String sql = "select notificationstatus from user where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			
			if (rs.getInt("notificationstatus") == 0)
				return false;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public int setNotificationStatus(String email, boolean value) {
		int ret = 0;
		try {

			String sql = "";
			if (value)
				sql = "update user set notificationStatus = 1 where email = ?";
			else
				sql = "update user set notificationStatus = 0 where email = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ret = ps.executeUpdate();

			if (value == false) {
				sql = "delete from notificationlist where email = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, email);
				ret = ps.executeUpdate();
			} else {
				sql = "insert into notificationlist(email) values(?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, email);
				ret = ps.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ArrayList<String> getPushNotificationList() {
		ArrayList<String> list = new ArrayList<String>();

		try {
			String sql = "select * from notificationlist";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<User> getUserEnrolledList(int id) {
		List<User> list = new ArrayList<User>();

		try {
			String sql = "select * from job_" + id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				User u = new User(rs.getString("fullname"), rs.getString("email"), rs.getInt("experience"));
				list.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
