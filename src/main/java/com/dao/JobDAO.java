package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Job;
import com.entity.User;

public class JobDAO {

	private Connection conn;

	public JobDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean makeJobEntry(Job j) {
		try {

			String sql = "insert into job(role,location,pay,dateOfInterview,description,jobStatus,code) values(?,?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, j.getRole());
			ps.setString(2, j.getLocation());
			ps.setString(3, j.getPay());
			ps.setString(4, j.getDateOfInterview());
			ps.setString(5, j.getDescription());
			ps.setInt(6, j.getJobStatus());
			ps.setInt(7, j.getCode());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean makeJobTable(int id) {

		try {

			String sql = "CREATE TABLE job_" + id
					+ "(`id` INT NOT NULL AUTO_INCREMENT,`email` VARCHAR(55) NULL, `fullname` VARCHAR(45) NULL,\r\n"
					+ "  `experience` INT NULL,PRIMARY KEY (`id`))";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean addJob(Job j) {
		System.out.println(j);
		try {

			if (makeJobEntry(j) == false) {
				System.out.println("couldn't make job entry in table.");
				return false;
			}

			String sql = "select * from job where code = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, j.getCode());
			ResultSet rs = ps.executeQuery();

			if (rs.next() == false || makeJobTable(rs.getInt("id")) == false)
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editJob(Job j) {

		try {

			String sql = "update job set role = ?, location = ?, pay = ?, dateOfInterview = ?, description = ?, jobStatus = ? where code = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, j.getRole());
			ps.setString(2, j.getLocation());
			ps.setString(3, j.getPay());
			ps.setString(4, j.getDateOfInterview());
			ps.setString(5, j.getDescription());
			ps.setInt(6, j.getJobStatus());
			ps.setInt(7, j.getCode());

			if (ps.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Job> getAllJob() {
		List<Job> list = new ArrayList<Job>();
		Job j = null;

		try {
			String sql = "select * from carriere.job order by id desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				j = new Job();
				j.setId(rs.getInt(1));
				j.setRole(rs.getString(2));
				j.setLocation(rs.getString(3));
				j.setPay(rs.getString(4));
				j.setDateOfInterview(rs.getString(5));
				j.setDescription(rs.getString(6));
				j.setJobStatus(rs.getInt(7));
				j.setCode(rs.getInt(8));
				list.add(j);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

//	public List<Job> getAllJobForUser() {
//		List<Job> list = new ArrayList<Job>();
//		Job j = null;
//
//		try {
//			String sql = "select * from carriere.job where jobStatus = 1 order by id desc";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				j = new Job();
//				j.setId(rs.getInt(1));
//				j.setRole(rs.getString(2));
//				j.setLocation(rs.getString(3));
//				j.setPay(rs.getString(4));
//				j.setDateOfInterview(rs.getString(5));
//				j.setDescription(rs.getString(6));
//				j.setPdate(rs.getTimestamp(7) + "");
//				list.add(j);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//
//	}
//
//	public List<Job> getJobFiltered(String location, String category) {
//		List<Job> list = new ArrayList<Job>();
//		Job j = null;
//
//		try {
//			String sql = "select * from carriere.job where location = ? and category = ? and status = 'Active' order by id desc";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, location);
//			ps.setString(2, category);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				j = new Job();
//				j.setID(rs.getInt(1));
//				j.setTitle(rs.getString(2));
//				j.setDescription(rs.getString(3));
//				j.setCategory(rs.getString(4));
//				j.setStatus(rs.getString(5));
//				j.setLocation(rs.getString(6));
//				j.setPdate(rs.getTimestamp(7) + "");
//				list.add(j);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//
//	}

	public Job getJobById(int id) {
		Job j = null;
		try {
			String sql = "select * from carriere.job where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				j = new Job();
				j.setId(rs.getInt(1));
				j.setRole(rs.getString(2));
				j.setLocation(rs.getString(3));
				j.setPay(rs.getString(4));
				j.setDateOfInterview(rs.getString(5));
				j.setDescription(rs.getString(6));
				j.setJobStatus(rs.getInt(7));
				j.setCode(rs.getInt(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	public boolean deleteJob(int id) {

		try {
			String sql = "delete from job where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("Id Value : " + id);

			sql = "drop table job_" + id;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public boolean getEnrollStatus(int id, String email) {
		try {
			
			String sql = "select * from job_" + id + " where email ='" + email+ "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(sql);
			if( rs.next() )
				return true;

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean addUserToJob(int id, User u) {
		
		try {
			String sql = "insert into job_" + id + "(email,fullname,experience) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,u.getEmail());
			ps.setString(2, u.getFullname());
			ps.setInt(3, u.getExperience());
			
			ps.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public ArrayList<String> getEnrolledEmails(int id) {
		
		ArrayList<String> list = new ArrayList<String>();

		try {
			String sql = "select email from carriere.job_"+ id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String s = rs.getString(1);
				list.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean removeUserFromJob(int id, String email) {
		try {
			String sql = "delete from job_" + id + " where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,email);
			ps.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
