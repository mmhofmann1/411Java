//Matthew Hofmann
//Final project
//Dao.java
//5/10/19
//this file holds all of the methods and functionality in the program
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
public class Dao {
	static Statement stmt = null;
	static DbConnect conn = null;
	static ByteArrayOutputStream baos = new ByteArrayOutputStream();
	static PrintStream ps = new PrintStream(baos);
	static PrintStream old = System.out;
	// constructor
		public Dao() { //create db object instance
			conn = new DbConnect();
	}
		// CREATE TABLE METHOD
		public void createTable() throws SQLException{
		 // Open a connection
		System.out.println("Connecting to database to create Table...");
		System.out.println("Connected database successfully...");
		// Execute create query
		System.out.println("Creating data table in given database...");
		stmt = conn.connect().createStatement();
			String sql = "CREATE TABLE m_hofm_tickets " + 
			             "(tid INTEGER not NULL AUTO_INCREMENT, " +
			             " name VARCHAR(20), " + 
			            " ticket_desc VARCHAR(50), " + 
					 " start_date DATETIME, " +
					" end_date DATETIME, " +
				 " PRIMARY KEY ( tid ))";
		//stmt.executeUpdate(sql);
		System.out.println("Created table in given database...");
		System.out.println("Creating credentials table in given database...");
		stmt = conn.connect().createStatement();
			String sql1 = "CREATE TABLE m_hofm_users " + 
			             "(uid INTEGER not NULL AUTO_INCREMENT, " +
			             " username VARCHAR(20) UNIQUE, " + 
			            " password VARCHAR(20), " +
			           " admin BIT," +
				 " PRIMARY KEY ( uid ))";
		//stmt.executeUpdate(sql1);
		System.out.println("Created table in given database...");
		//create admin login
		stmt = conn.connect().createStatement();
		System.out.println("Creating default admin user...");
		String sql2 = "INSERT INTO m_hofm_users(uid, username, password, admin)" + "VALUES ( 1, 'admin', 'admin', 1)";
		System.out.println("Default admin user created...");
		stmt.executeUpdate(sql2);
		conn.connect().close(); //close db connection 
		      }
		//method that retrieves ticket data given a certain id number in the database
		public ResultSet retrieveTicketRecords(String a1) throws SQLException {
			stmt = conn .connect().createStatement();
			System.out.println("Retrieving tickets from database...");
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_tickets WHERE tid = " +a1);
			rs.last();
			int size = rs.getRow();
			rs.first();
			if (!rs.isFirst()) {
				return rs;
			}
			int i;
			do {
				for (i = 1; i<=size; i++) {
					if(Integer.parseInt(a1) == i) {
						break;
					}
				}
				if(Integer.parseInt(a1) != i) {
					System.setOut(ps);
					System.out.println("Ticket id does not exist");
					System.out.flush();
					System.setOut(old);
					System.out.println("Ticket id does not exist");
					return rs;
				}
			} while (Integer.parseInt(a1) != i);
			System.out.println("Records retrieved...");
			conn.connect().close();
			 return rs;
			}
		//this method retrieves all of the tickets in the database
		public void retrieveAllTickets() throws SQLException {
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from m_hofm_tickets");
			new ViewTwo().runView(rs); // display records in window
			rs.close();
			conn.connect().close();
		}
		//this method inserts a user into the database
		public void insertUser(String a1, String a2, String a3) throws SQLException {
			baos.reset();
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_users");
			rs.last();
			int inc = rs.getInt("uid");
			String sql5 = "ALTER TABLE m_hofm_users AUTO_INCREMENT = " +inc;
			rs.beforeFirst();
			while(rs.next()) {
				if(a1.trim().equals(rs.getString("username").trim())) {
					System.setOut(ps);
					System.out.println("Username already exists");
					System.setOut(old);
					return;
				}
			}
			stmt.executeUpdate(sql5);
			System.out.println("Inserting user into database...");
			String sql3 = "INSERT INTO m_hofm_users(username, password, admin)" + "VALUES ('"+a1+"', '"+a2+"', "+a3+")";
			stmt.executeUpdate(sql3);
			System.out.println("User inserted into database...");
			conn.connect().close();
		}
		//this method updates a user in the database
		public void updateUser(String a1, String a2, String a3, String a4) throws SQLException{
			baos.reset();
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_users");
			String[] idarray = new String[0];
			ArrayList<String> idlist = new ArrayList<String>(Arrays.asList(idarray));
			rs.beforeFirst();
			while (rs.next()) {
				String id = rs.getString("uid");
				String add = id;
				idlist.add(add);
			}
			idarray = idlist.toArray(idarray);
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			int i;
			equal: {
			for(i = 0; i < idarray.length; i++) 
				if(a1.trim().equals(idarray[i])) {
					break equal;
				}
			System.setOut(ps);
			System.out.println("User id does not exist");
			System.setOut(old);
			return;
			}
			rs.beforeFirst();
			while(rs.next()) {
				if(a2.trim().equals(rs.getString("username").trim())) {
					System.setOut(ps);
					System.out.println("Username already exists");
					System.setOut(old);
					return;
				}
			}
			if(Integer.parseInt(a4) != 0 || Integer.parseInt(a4) != 1) {
				System.setOut(ps);
				System.out.println("Invalid key input");
				System.setOut(old);
				return;
			}
			System.out.println("Updating user...");
			String sql11 = "UPDATE m_hofm_users SET username = '" +a2+ "', password = '" +a3+ "', admin = " +a4+ " WHERE uid = " +a1;
			stmt.executeUpdate(sql11);
			System.out.println("User updated");
			conn.connect().close();
		}
		//this method deletes a user in the database
		public void deleteUser(String a1) throws SQLException{
			baos.reset();
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_users");
			String[] idarray = new String[0];
			ArrayList<String> idlist = new ArrayList<String>(Arrays.asList(idarray));
			rs.beforeFirst();
			while (rs.next()) {
				String id = rs.getString("uid");
				String add = id;
				idlist.add(add);
			}
			idarray = idlist.toArray(idarray);
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			int i;
			equal: {
			for(i = 0; i < idarray.length; i++) 
				if(a1.trim().equals(idarray[i])) {
					System.setOut(ps);
					System.out.println("User deleted");
					System.setOut(old);
					break equal;
				}
			System.setOut(ps);
			System.out.println("User id does not exist");
			System.setOut(old);
			return;
			}
			System.out.println("Deleting user from database...");
			String sql4 = "DELETE FROM m_hofm_users WHERE uid = " +a1;
			stmt.executeUpdate(sql4);
			System.out.println("User deleted from database...");
			conn.connect().close();
		}
		//this method submits a new ticket into the database
		public void insertTicket(String a1, String a2) throws SQLException{
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_tickets");
			int size = rs.getRow();
			if(size == 0) {
				String sql8 = "ALTER TABLE m_hofm_tickets AUTO_INCREMENT = 1";
				stmt.executeUpdate(sql8);
			} else {
				rs.last();
				int inc = rs.getInt("uid");
				String sql8 = "ALTER TABLE m_hofm_tickets AUTO_INCREMENT = " +inc;
				stmt.executeUpdate(sql8);
			}
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
			System.out.println("Inserting ticket into database...");
			String sql6 = "INSERT INTO m_hofm_tickets(name, ticket_desc, start_date)" + "VALUES ('" +a1+"', '" +a2+"', '" +date+"')";
			stmt.executeUpdate(sql6);
			System.out.println("Ticket inserted into database");
			conn.connect().close();
		}
		//this method closes a ticket in the database 
		public void closeTicket(String a1) throws SQLException{
			baos.reset();
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_tickets");
			rs.last();
			int size = rs.getRow();
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			String[] idarray = new String[0];
			ArrayList<String> idlist = new ArrayList<String>(Arrays.asList(idarray));
			rs.beforeFirst();
			while (rs.next()) {
				String id = rs.getString("tid");
				String add = id;
				idlist.add(add);
			}
			idarray = idlist.toArray(idarray);
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			int i;
			equal: {
			for(i = 0; i < idarray.length; i++) 
				if(a1.trim().equals(idarray[i])) {
					System.setOut(ps);
					System.out.println("Ticket closed");
					System.setOut(old);
					break equal;
				}
			System.setOut(ps);
			System.out.println("Ticket id does not exist");
			System.setOut(old);
			return;
			}
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
			System.out.println("Closing ticket...");
			String sql7 = "UPDATE m_hofm_tickets SET end_date = '" +date+ "' WHERE tid = " +a1;
			stmt.executeUpdate(sql7);
			System.out.println("Ticket closed");
			conn.connect().close();
		}
		//this method deletes a ticket in the database
		public void deleteTicket(String a1) throws SQLException{
			baos.reset();
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_tickets");
			rs.last();
			int size = rs.getRow();
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			String[] idarray = new String[0];
			ArrayList<String> idlist = new ArrayList<String>(Arrays.asList(idarray));
			rs.beforeFirst();
			while (rs.next()) {
				String id = rs.getString("tid");
				String add = id;
				idlist.add(add);
			}
			idarray = idlist.toArray(idarray);
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			int i;
			equal: {
			for(i = 0; i < idarray.length; i++) 
				if(a1.trim().equals(idarray[i])) {
					System.setOut(ps);
					System.out.println("Ticket deleted");
					System.setOut(old);
					break equal;
				}
			System.setOut(ps);
			System.out.println("Ticket id does not exist");
			System.setOut(old);
			return;
			}
			System.out.println("Deleting ticket from database...");
			String sql9 = "DELETE FROM m_hofm_tickets WHERE tid = " +a1;
			stmt.executeUpdate(sql9);
			System.out.println("Ticket deleted from database...");
			conn.connect().close();
		}
		//this method updates a ticket in the database
		public void updateTick(String a1, String a2, String a3) throws SQLException{
			baos.reset();
			stmt = conn .connect().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from m_hofm_tickets");
			rs.last();
			int size = rs.getRow();
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			String[] idarray = new String[0];
			ArrayList<String> idlist = new ArrayList<String>(Arrays.asList(idarray));
			rs.beforeFirst();
			while (rs.next()) {
				String id = rs.getString("tid");
				String add = id;
				idlist.add(add);
			}
			idarray = idlist.toArray(idarray);
			rs.first();
			if (!rs.isFirst()) {
				return;
			}
			int i;
			equal: {
			for(i = 0; i < idarray.length; i++) 
				if(a1.trim().equals(idarray[i])) {
					System.setOut(ps);
					System.out.println("Ticket updated");
					System.setOut(old);
					break equal;
				}
			System.setOut(ps);
			System.out.println("Ticket id does not exist");
			System.setOut(old);
			return;
			}
			System.out.println("Updating ticket...");
			String sql10 = "UPDATE m_hofm_tickets SET name = '" +a2+ "', ticket_desc = '" +a3+ "' WHERE tid = " +a1;
			stmt.executeUpdate(sql10);
			System.out.println("Ticket updated");
			conn.connect().close();
			return;
		}
}