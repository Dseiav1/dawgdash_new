package dawgdash.dbaccess;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import dawgdash.entities.*;

public class DBHelper {

	//private static final String URL = "jdbc:mysql://localhost/dawgdash";
	//private static final String USER = "root";
	//private static final String PASS = "Alhod,!^#@";
	
	
	private static final String URL = "jdbc:mysql://localhost/dawgdash";
	private static final String USER = "athena";
	private static final String PASS = "wisdomgoddess";

	protected PreparedStatement selectUsernameStatement;
	protected PreparedStatement selectEmailStatement;
	protected PreparedStatement selectIdStatement;
	protected PreparedStatement addWorkerStatement;
	protected PreparedStatement addCustomerStatement;
	protected PreparedStatement selectAllUsersStatement;
	protected PreparedStatement changeEmailStatement;
	protected PreparedStatement changePasswordStatement;
	protected PreparedStatement changeAddressStatement;
	protected PreparedStatement changeTransportationStatement;
	protected PreparedStatement workerCommentDeliveryStatement;
	protected PreparedStatement changeDeliveryStatusStatement;
	protected PreparedStatement selectDeliveryStatement;
	protected PreparedStatement selectCustomerPendingStatement;
	protected PreparedStatement selectWorkerPendingStatement;
	protected PreparedStatement selectPreviousDeliveriesStatement;
	protected PreparedStatement selectScheduleStatement;
	protected PreparedStatement updateScheduleStatement;
	protected PreparedStatement addDeliveryStatement;
	protected PreparedStatement selectSundayWorkerStatement;
	protected PreparedStatement selectMondayWorkerStatement;
	protected PreparedStatement selectTuesdayWorkerStatement;
	protected PreparedStatement selectWednesdayWorkerStatement;
	protected PreparedStatement selectThursdayWorkerStatement;
	protected PreparedStatement selectFridayWorkerStatement;
	protected PreparedStatement selectSaturdayWorkerStatement;
	protected PreparedStatement createScheduleStatement;
	protected PreparedStatement incrementPendingStatement;
	protected PreparedStatement decrementPendingStatement;

	protected PreparedStatement addNewDeliveryStatement;
	protected PreparedStatement assignDeliveryStatement;

	public DBHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			/*
			 * Connection conn = null; Properties connectionProps = new
			 * Properties(); connectionProps.put("user", "root");
			 * connectionProps.put("password", "root"); conn =
			 * DriverManager.getConnection("jdbc:mysql://localhost/dawgdash",
			 * connectionProps);
			 */
			selectUsernameStatement = conn
					.prepareStatement("select * from users where username=?");
			selectEmailStatement = conn
					.prepareStatement("select * from users where email=?");
			selectIdStatement = conn
					.prepareStatement("select * from users where id=?");
			addWorkerStatement = conn
					.prepareStatement(
							"insert into users(name, email, username, password, transportation, role) values(?,?,?,?,?,'worker')",
							Statement.RETURN_GENERATED_KEYS);
			addCustomerStatement = conn
					.prepareStatement("insert into users(name, email, username, password, default_address, role) values(?,?,?,?,?,'customer')");
			selectAllUsersStatement = conn
					.prepareStatement("select * from users");
			changeEmailStatement = conn
					.prepareStatement("update users set email=? where id=?");
			changePasswordStatement = conn
					.prepareStatement("update users set password=? where id=?");
			changeAddressStatement = conn
					.prepareStatement("update users set default_address=? where id=?");
			changeTransportationStatement = conn
					.prepareStatement("update users set transportation=? where id=?");
			workerCommentDeliveryStatement = conn
					.prepareStatement("update deliveries set worker_comments=? where id=?");
			changeDeliveryStatusStatement = conn
					.prepareStatement("update deliveries set status=? where id=?");
			selectDeliveryStatement = conn
					.prepareStatement("select * from deliveries where id=?");
			selectCustomerPendingStatement = conn
					.prepareStatement("select * from deliveries where status='pending' and customer_id=? order by pickup_time");
			selectWorkerPendingStatement = conn
					.prepareStatement("select * from deliveries where status='in progress' and worker_id=? order by pickup_time");
			selectPreviousDeliveriesStatement = conn
					.prepareStatement("select * from deliveries where status='completed' and customer_id=?");
			selectScheduleStatement = conn
					.prepareStatement("select * from schedules where worker_id=?");
			updateScheduleStatement = conn
					.prepareStatement("update schedules set sunday_start=?, sunday_end=?, monday_start=?, monday_end=?, tuesday_start=?, tuesday_end=?, wednesday_start=?, wednesday_end=?, thursday_start=?, thursday_end=?, friday_start=?, friday_end=?, saturday_start=?, saturday_end=? where worker_id=?");
			addDeliveryStatement = conn
					.prepareStatement("insert into deliveries(customer_id, worker_id, pickup_time, pickup_address, destination_address, instructions, description, price, quantity, transportation) values(?,?,?,?,?,?,?,?,?,?)");
			selectSundayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.sunday_start <= ? and ? < schedules.sunday_end and users.transportation >= ? order by users.pending_deliveries");
			selectMondayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.monday_start <= ? and ? < schedules.monday_end and users.transportation >= ? order by users.pending_deliveries");
			selectTuesdayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.tuesday_start <= ? and ? < schedules.tuesday_end and users.transportation >= ? order by users.pending_deliveries");
			selectWednesdayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.wednesday_start <= ? and ? < schedules.wednessday_end and users.transportation >= ? order by users.pending_deliveries");
			selectThursdayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.thursday_start <= ? and ? < schedules.thursday_end and users.transportation >= ? order by users.pending_deliveries");
			selectFridayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.fridayday_start <= ? and ? < schedules.friday_end and users.transportation >= ? order by users.pending_deliveries");
			selectSaturdayWorkerStatement = conn
					.prepareStatement("select schedules.worker_id from schedules join users on users.id = schedules.worker_id where schedules.saturday_start <= ? and ? < schedules.saturday_end and users.transportation >= ? order by users.pending_deliveries");
			createScheduleStatement = conn
					.prepareStatement("insert into schedules(worker_id) values(?)");
			incrementPendingStatement = conn
					.prepareStatement("update users set pending_deliveries=pending_deliveries+1 where id=?");
			decrementPendingStatement = conn
					.prepareStatement("update users set pending_deliveries=pending_deliveries-1 where id=?");

			addNewDeliveryStatement = conn
					.prepareStatement("insert into deliveries(customer_id, worker_id, pickup_time, pickup_address, destination_address, instructions, description, price, quantity, transportation) values(?,1,?,?,?,?,?,?,?,?)");
			assignDeliveryStatement = conn
					.prepareStatement("update deliveries set worker_id=? where id=?");

		} catch (Exception e) {
			System.out.println("Exception in DBHelper(): " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void addWorkerComment(int id, String comment) {
		try {
			workerCommentDeliveryStatement.setString(1, comment);
			workerCommentDeliveryStatement.setInt(2, id);
			workerCommentDeliveryStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in addWorkerComment(): "
					+ e.getMessage());
		}
	}

	public void assignDelivery(int deliveryId) {
		try {
			selectDeliveryStatement.setInt(1, deliveryId);
			ResultSet rs = selectDeliveryStatement.executeQuery();
			rs.next();
			int time = rs.getInt("pickup_time");
			int transportationNeeded = rs.getInt("transportation");
			int workerId = getWorkerForDelivery(time, transportationNeeded);
			assignDeliveryStatement.setInt(1, workerId);
			assignDeliveryStatement.setInt(2, deliveryId);
			assignDeliveryStatement.executeUpdate();
			incrementPendingStatement.setInt(1, workerId);
			incrementPendingStatement.executeUpdate();
			changeDeliveryStatusStatement.setString(1, "in progress");
			changeDeliveryStatusStatement.setInt(2, deliveryId);
			changeDeliveryStatusStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in assignDelivery(): "
					+ e.getMessage());
		}
	}

	public String attemptLogin(String loginId, String password) {
		try {
			selectUsernameStatement.setString(1, loginId);
			ResultSet rs = selectUsernameStatement.executeQuery();
			if (!rs.next()) {
				return "login error";
			}
			String storedPassword = rs.getString("password");
			if (storedPassword.equals(hash(password))) {
				return rs.getString("role");
			} else {
				return "login error";
			}
		} catch (Exception e) {
			System.out
					.println("Exception in attemptLogin(): " + e.getMessage());
			return "login error";
		}
	}

	public boolean availableEmail(String email) {
		try {
			selectEmailStatement.setString(1, email);
			ResultSet rs = selectEmailStatement.executeQuery();
			return !rs.next();
		} catch (Exception e) {
			System.out.println("Exception in availableEmail(): "
					+ e.getMessage());
			return false;
		}
	}

	public boolean availableUsername(String username) {
		try {
			selectUsernameStatement.setString(1, username);
			ResultSet rs = selectUsernameStatement.executeQuery();
			return !rs.next();
		} catch (Exception e) {
			System.out.println("Exception in availableUsername(): "
					+ e.getMessage());
			return false;
		}
	}

	public void cancelDelivery(int id) {
		try {
			changeDeliveryStatusStatement.setString(1, "canceled");
			changeDeliveryStatusStatement.setInt(2, id);
			changeDeliveryStatusStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in cancelDelivery(): "
					+ e.getMessage());
		}
	}

	public void completeDelivery(int deliveryId, int workerId,
			String workerComments) {
		try {
			workerCommentDeliveryStatement.setString(1, workerComments);
			workerCommentDeliveryStatement.setInt(2, deliveryId);
			workerCommentDeliveryStatement.executeUpdate();
			changeDeliveryStatusStatement.setString(1, "completed");
			changeDeliveryStatusStatement.setInt(2, deliveryId);
			changeDeliveryStatusStatement.executeUpdate();
			decrementPendingStatement.setInt(1, workerId);
			decrementPendingStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in completeDelivery(): "
					+ e.getMessage());
		}
	}

	public void createCustomer(String name, String email, String username,
			String unhashedPassword, String defaultAddress) {
		try {
			addCustomerStatement.setString(1, name);
			addCustomerStatement.setString(2, email);
			addCustomerStatement.setString(3, username);
			addCustomerStatement.setString(4, hash(unhashedPassword));
			addCustomerStatement.setString(5, defaultAddress);
			addCustomerStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in createCustomer(): "
					+ e.getMessage());
		}
	}

	// transportation: 0-undefined, 1-bike, 2-car, 3-truck
	public void createWorker(String name, String email, String username,
			String unhashedPassword, int transportation) {
		try {
			addWorkerStatement.setString(1, name);
			addWorkerStatement.setString(2, email);
			addWorkerStatement.setString(3, username);
			addWorkerStatement.setString(4, hash(unhashedPassword));
			addWorkerStatement.setInt(5, transportation);
			addWorkerStatement.executeUpdate();
			ResultSet rs = addWorkerStatement.getGeneratedKeys();
			rs.next();
			int workerId = rs.getInt(1);
			createScheduleStatement.setInt(1, workerId);
			createScheduleStatement.executeUpdate();
		} catch (Exception e) {
			System.out
					.println("Exception in createWorker(): " + e.getMessage());
		}
	}

	public ArrayList<User> getAllUsers() {
		try {
			ArrayList<User> users = new ArrayList<User>();
			ResultSet rs = selectAllUsersStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String defaultAddress = rs.getString("default_address");
				int transportation = rs.getInt("transportation");
				String role = rs.getString("role");
				User user = new User(id, name, email, username, password,
						defaultAddress, transportation, role);
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			System.out.println("Exception in getAllUsers(): " + e.getMessage());
			return new ArrayList<User>();
		}
	}

	public String getDefaultAddress(int id) {
		try {
			selectIdStatement.setInt(1, id);
			ResultSet rs = selectIdStatement.executeQuery();
			if (!rs.next()) {
				return "error";
			} else {
				return rs.getString("default_address");
			}
		} catch (Exception e) {
			System.out.println("Exception in getDefaultAddress(): "
					+ e.getMessage());
			return "error";
		}
	}

	public Delivery getDelivery(int id) {
		try {
			selectDeliveryStatement.setInt(1, id);
			ResultSet rs = selectDeliveryStatement.executeQuery();
			if (!rs.next()) {
				return null;
			} else {
				// construct delivery object and return it
				int _id = rs.getInt("id");
				int customerId = rs.getInt("customer_id");
				int workerId = rs.getInt("worker_id");
				Timestamp date = rs.getTimestamp("date");
				int pickupTime = rs.getInt("pickup_time");
				String pickupAddress = rs.getString("pickup_address");
				String destinationAddress = rs.getString("destination_address");
				String instructions = rs.getString("instructions");
				String description = rs.getString("description");
				String workerComments = rs.getString("worker_comments");
				String status = rs.getString("status");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				int transportation = rs.getInt("transportation");
				Delivery delivery = new Delivery(_id, customerId, workerId,
						date, pickupTime, pickupAddress, destinationAddress,
						instructions, description, workerComments, status,
						price, quantity, transportation);
				return delivery;
			}
		} catch (Exception e) {
			System.out.println("Exception in getDelivery(): " + e.getMessage());
			return null;
		}
	}

	public int getIdForUsername(String username) {
		try {
			selectUsernameStatement.setString(1, username);
			ResultSet rs = selectUsernameStatement.executeQuery();
			if (!rs.next()) {
				return -1;
			} else {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			System.out.println("Exception in getIdForUsername(): "
					+ e.getMessage());
			return -1;
		}
	}

	public String getPassword(int id) {
		try {
			selectIdStatement.setInt(1, id);
			ResultSet rs = selectIdStatement.executeQuery();
			if (!rs.next()) {
				return "error";
			} else {
				return rs.getString("password");
			}
		} catch (Exception e) {
			System.out.println("Exception in getPassword(): " + e.getMessage());
			return "error";
		}
	}

	public ArrayList<Delivery> getPendingCustomerDeliveries(int id) {
		try {
			selectCustomerPendingStatement.setInt(1, id);
			ResultSet rs = selectCustomerPendingStatement.executeQuery();
			ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
			while (rs.next()) {
				int _id = rs.getInt("id");
				int customerId = rs.getInt("customer_id");
				int workerId = rs.getInt("worker_id");
				Timestamp date = rs.getTimestamp("date");
				int pickupTime = rs.getInt("pickup_time");
				String pickupAddress = rs.getString("pickup_address");
				String destinationAddress = rs.getString("destination_address");
				String instructions = rs.getString("instructions");
				String description = rs.getString("description");
				String workerComments = rs.getString("worker_comments");
				String status = rs.getString("status");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				int transportation = rs.getInt("transportation");
				Delivery delivery = new Delivery(_id, customerId, workerId,
						date, pickupTime, pickupAddress, destinationAddress,
						instructions, description, workerComments, status,
						price, quantity, transportation);
				deliveries.add(delivery);
			}
			return deliveries;

		} catch (Exception e) {
			System.out.println("Exception in getPendingCustomerDeliveries(): "
					+ e.getMessage());
			return new ArrayList<Delivery>();
		}
	}

	public ArrayList<Delivery> getPendingWorkerDeliveries(int id) {
		try {
			selectWorkerPendingStatement.setInt(1, id);
			ResultSet rs = selectWorkerPendingStatement.executeQuery();
			ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
			while (rs.next()) {
				int _id = rs.getInt("id");
				int customerId = rs.getInt("customer_id");
				int workerId = rs.getInt("worker_id");
				Timestamp date = rs.getTimestamp("date");
				int pickupTime = rs.getInt("pickup_time");
				String pickupAddress = rs.getString("pickup_address");
				String destinationAddress = rs.getString("destination_address");
				String instructions = rs.getString("instructions");
				String description = rs.getString("description");
				String workerComments = rs.getString("worker_comments");
				String status = rs.getString("status");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				int transportation = rs.getInt("transportation");
				Delivery delivery = new Delivery(_id, customerId, workerId,
						date, pickupTime, pickupAddress, destinationAddress,
						instructions, description, workerComments, status,
						price, quantity, transportation);
				deliveries.add(delivery);
			}
			return deliveries;

		} catch (Exception e) {
			System.out.println("Exception in getPendingWorkerDeliveries(): "
					+ e.getMessage());
			return new ArrayList<Delivery>();
		}

	}

	public ArrayList<Delivery> getPreviousDeliveries(int id) {
		try {
			selectPreviousDeliveriesStatement.setInt(1, id);
			ResultSet rs = selectPreviousDeliveriesStatement.executeQuery();
			ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
			while (rs.next()) {
				int _id = rs.getInt("id");
				int customerId = rs.getInt("customer_id");
				int workerId = rs.getInt("worker_id");
				Timestamp date = rs.getTimestamp("date");
				int pickupTime = rs.getInt("pickup_time");
				String pickupAddress = rs.getString("pickup_address");
				String destinationAddress = rs.getString("destination_address");
				String instructions = rs.getString("instructions");
				String description = rs.getString("description");
				String workerComments = rs.getString("worker_comments");
				String status = rs.getString("status");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				int transportation = rs.getInt("transportation");
				Delivery delivery = new Delivery(_id, customerId, workerId,
						date, pickupTime, pickupAddress, destinationAddress,
						instructions, description, workerComments, status,
						price, quantity, transportation);
				deliveries.add(delivery);
			}
			return deliveries;
		} catch (Exception e) {
			System.out.println("Exception in getPreviousDeliveries(): "
					+ e.getMessage());
			return new ArrayList<Delivery>();
		}
	}

	public Schedule getSchedule(int id) {
		try {
			selectScheduleStatement.setInt(1, id);

			ResultSet rs = selectScheduleStatement.executeQuery();
			if (!rs.next()) {
				return null;
			} else {
				int _id = rs.getInt("worker_id");
				int sundayStart = rs.getInt("sunday_start");
				int sundayEnd = rs.getInt("sunday_end");
				int mondayStart = rs.getInt("monday_start");
				int mondayEnd = rs.getInt("monday_end");
				int tuesdayStart = rs.getInt("tuesday_start");
				int tuesdayEnd = rs.getInt("tuesday_end");
				int wednesdayStart = rs.getInt("wednesday_start");
				int wednesdayEnd = rs.getInt("wednesday_end");
				int thursdayStart = rs.getInt("thursday_start");
				int thursdayEnd = rs.getInt("thursday_end");
				int fridayStart = rs.getInt("friday_start");
				int fridayEnd = rs.getInt("friday_end");
				int saturdayStart = rs.getInt("saturday_start");
				int saturdayEnd = rs.getInt("saturday_end");
				selectIdStatement.setInt(1, _id);
				ResultSet rs2 = selectIdStatement.executeQuery();
				rs2.next();
				String name = rs2.getString("name");
				Schedule schedule = new Schedule(_id, name, sundayStart,
						sundayEnd, mondayStart, mondayEnd, tuesdayStart,
						tuesdayEnd, wednesdayStart, wednesdayEnd,
						thursdayStart, thursdayEnd, fridayStart, fridayEnd,
						saturdayStart, saturdayEnd);
				return schedule;
			}

		} catch (Exception e) {
			System.out.println("Exception in getSchedule(): " + e.getMessage());
			return null;
		}
	}

	public User getUser(int id) {
		try {
			selectIdStatement.setInt(1, id);
			ResultSet rs = selectIdStatement.executeQuery();
			if (!rs.next()) {
				return null;
			} else {
				int _id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String defaultAddress = rs.getString("default_address");
				int transportation = rs.getInt("transportation");
				String role = rs.getString("role");
				User user = new User(_id, name, email, username, password,
						defaultAddress, transportation, role);
				return user;
			}
		} catch (Exception e) {
			System.out.println("Exception in getUser(): " + e.getMessage());
			return null;
		}
	}

	public ArrayList<User> getWorkers() {
		try {
			ArrayList<User> users = new ArrayList<User>();
			ResultSet rs = selectAllUsersStatement.executeQuery();
			while (rs.next()) {
				if (rs.getString("role").equals("worker")) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String defaultAddress = rs.getString("default_address");
					int transportation = rs.getInt("transportation");
					String role = rs.getString("role");
					User user = new User(id, name, email, username, password,
							defaultAddress, transportation, role);
					users.add(user);
				}
			}
			return users;
		} catch (Exception e) {
			System.out.println("Exception in getAllUsers(): " + e.getMessage());
			return new ArrayList<User>();
		}
	}

	public String hash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes(), 0, password.length());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			System.out.println("Exception in hash(): " + e.getMessage());
			return "error";
		}
	}

	public void makeAllDeliveriesInProgress(int id) {
		try {
			selectCustomerPendingStatement.setInt(1, id);
			ResultSet rs = selectCustomerPendingStatement.executeQuery();
			while (rs.next()) {
				int deliveryId = rs.getInt("id");
				assignDelivery(deliveryId);
			}
		} catch (Exception e) {
			System.out.println("Exception in makeAllDeliveriesInProgress(): "
					+ e.getMessage());
		}
	}

	public void markUncompletable(int deliveryId, int workerId) {
		try {
			workerCommentDeliveryStatement.setString(1,
					"Unable to complete delivery");
			workerCommentDeliveryStatement.setInt(2, deliveryId);
			workerCommentDeliveryStatement.executeUpdate();
			changeDeliveryStatusStatement.setString(1, "Uncompletable");
			changeDeliveryStatusStatement.setInt(2, deliveryId);
			changeDeliveryStatusStatement.executeUpdate();
			decrementPendingStatement.setInt(1, workerId);
			decrementPendingStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in markUncompletable(): "
					+ e.getMessage());
		}

	}

	public void scheduleDelivery(int id, String pickupAddress,
			String destinationAddress, int quantity, int transportation,
			String description, String instructions, int pickupTime) {
		int price = calculatePrice(quantity, transportation);
		try {
			addNewDeliveryStatement.setInt(1, id);
			addNewDeliveryStatement.setInt(2, pickupTime);
			addNewDeliveryStatement.setString(3, pickupAddress);
			addNewDeliveryStatement.setString(4, destinationAddress);
			addNewDeliveryStatement.setString(5, instructions);
			addNewDeliveryStatement.setString(6, description);
			addNewDeliveryStatement.setInt(7, price);
			addNewDeliveryStatement.setInt(8, quantity);
			addNewDeliveryStatement.setInt(9, transportation);
			addNewDeliveryStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception in scheduleDelivery(): "
					+ e.getMessage());
		}
	}

	public void updateAddress(int id, String address) {
		try {
			changeAddressStatement.setString(1, address);
			changeAddressStatement.setInt(2, id);
			changeAddressStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in updateAddress(): "
					+ e.getMessage());
		}
	}

	public void updateEmail(int id, String email) {
		try {
			changeEmailStatement.setString(1, email);
			changeEmailStatement.setInt(2, id);
			changeEmailStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in updateEmail(): " + e.getMessage());
		}
	}

	public void updatePassword(int id, String password) {
		try {
			changePasswordStatement.setString(1, hash(password));
			changePasswordStatement.setInt(2, id);
			changePasswordStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in updatePassword(): "
					+ e.getMessage());
		}
	}

	public void updateSchedule(int id, int[] schedule) {
		try {
			updateScheduleStatement.setInt(15, id);
			for (int i = 0; i < 14; i++) {
				updateScheduleStatement.setInt(i + 1, schedule[i]);
			}
			updateScheduleStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in updateSchedule(): "
					+ e.getMessage());
		}
	}

	public void updateTransportation(int id, int transportation) {
		try {
			changeTransportationStatement.setInt(1, transportation);
			changeTransportationStatement.setInt(2, id);
			changeTransportationStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in updateTransportation(): "
					+ e.getMessage());
		}
	}

	private int getWorkerForDelivery(int time, int transportationNeeded) {
		// select workers with transportation greater than transportationNeeded
		// filter results by workers working on current day
		// filter results by workers whose start time is at or before time
		// filter results by workers whose end time is 30 minutes after time
		// sort results by number of pending deliveries
		// select worker with fewest pending deliveries

		// credit to Kyle Ivey on Stack Overflow for selecting day of week:
		// http://stackoverflow.com/questions/5574673
		try {
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			// select schedules.worker_id from schedules join users on users.id
			// = schedules.worker_id where schedules.sunday_start <= ? and
			// schedules.sunday_end < ? and users.transportation >= ? order by
			// users.pending_deliveries
			switch (day) {
				case 0: {
					selectSundayWorkerStatement.setInt(1, time);
					selectSundayWorkerStatement.setInt(2, time);
					selectSundayWorkerStatement.setInt(3, transportationNeeded);
					ResultSet rs = selectSundayWorkerStatement.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				case 1: {
					selectMondayWorkerStatement.setInt(1, time);
					selectMondayWorkerStatement.setInt(2, time);
					selectMondayWorkerStatement.setInt(3, transportationNeeded);
					ResultSet rs = selectMondayWorkerStatement.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				case 2: {
					selectTuesdayWorkerStatement.setInt(1, time);
					selectTuesdayWorkerStatement.setInt(2, time);
					selectTuesdayWorkerStatement
							.setInt(3, transportationNeeded);
					ResultSet rs = selectTuesdayWorkerStatement.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				case 3: {
					selectWednesdayWorkerStatement.setInt(1, time);
					selectWednesdayWorkerStatement.setInt(2, time);
					selectWednesdayWorkerStatement.setInt(3,
							transportationNeeded);
					ResultSet rs = selectWednesdayWorkerStatement
							.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				case 4: {
					selectThursdayWorkerStatement.setInt(1, time);
					selectThursdayWorkerStatement.setInt(2, time);
					selectThursdayWorkerStatement.setInt(3,
							transportationNeeded);
					ResultSet rs = selectThursdayWorkerStatement.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				case 5: {
					selectFridayWorkerStatement.setInt(1, time);
					selectFridayWorkerStatement.setInt(2, time);
					selectFridayWorkerStatement.setInt(3, transportationNeeded);
					ResultSet rs = selectFridayWorkerStatement.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				case 6: {
					selectSaturdayWorkerStatement.setInt(1, time);
					selectSaturdayWorkerStatement.setInt(2, time);
					selectSaturdayWorkerStatement.setInt(3,
							transportationNeeded);
					ResultSet rs = selectSaturdayWorkerStatement.executeQuery();
					if (!rs.next()) {
						return -1;
					} else {
						return rs.getInt(1);
					}
				}
				default: {
					return -1;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getWorkerForDelivery(): "
					+ e.getMessage());
			return -1;
		}
	}

	private int calculatePrice(int quantity, int size) {
		if (size == 1) {
			return 5;
		} else if (size == 2) {
			return 5 + quantity;
		} else if (size == 3) {
			return 5 + 2 * quantity;
		} else {
			return -1;
		}
	}

}