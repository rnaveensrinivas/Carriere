package com.mail;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

	public static void verifyAccount(String to, long code) {
		Properties properties = System.getProperties();
		System.out.println("Properties " + properties);

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1 : session object

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("tempnaveensrinivas@gmail.com", "zcemppcqnonpnxas");
			}
		});

		session.setDebug(true);
		// step 2 : composing message

		MimeMessage m = new MimeMessage(session);

		// from
		try {
			m.setFrom("tempnaveensrinivas@gmail.com");
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject("Account Verification");
			m.setText("Welcome to CARRIÈRE,\nClick this http://localhost:8534/Carriere/indexVerify.jsp?code=" + code
					+ " to verify your account.\n\n\nWith regards, Team.");

			// send
			// step 3

			Transport.send(m);

			System.out.println("sent");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void sendEmail(String message, String subject, String to) {

		Properties properties = System.getProperties();
		System.out.println("Properties " + properties);

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1 : session object

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("tempnaveensrinivas@gmail.com", "zcemppcqnonpnxas");
			}

		});

		session.setDebug(true);
		// step 2 : composing message

		MimeMessage m = new MimeMessage(session);

		// from
		try {
			m.setFrom("tempnaveensrinivas@gmail.com");
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);

			// send
			// step 3

			Transport.send(m);

			System.out.println("sent");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Address[] getEmailsList(ArrayList<String> emails) {

		Address[] emaiAddresses = new Address[emails.size()];

		for (int i = 0; i < emails.size(); i++) {
			try {
				emaiAddresses[i] = new InternetAddress(emails.get(i));
			} catch (AddressException e) {

				e.printStackTrace();
			}
		}
		return emaiAddresses;
	}

	public static boolean sendEmailGroup(String message, String subject, ArrayList<String> to) {

		Properties properties = System.getProperties();
		System.out.println("Properties " + properties);

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1 : session object

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("tempnaveensrinivas@gmail.com", "zcemppcqnonpnxas");
			}

		});

		session.setDebug(true);
		// step 2 : composing message

		MimeMessage m = new MimeMessage(session);

		// from
		try {
			m.setFrom("tempnaveensrinivas@gmail.com");
			m.addRecipients(Message.RecipientType.BCC, getEmailsList(to));
			m.setSubject(subject);
			m.setText(message);

			// send
			// step 3

			Transport.send(m);

			System.out.println("sent");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
