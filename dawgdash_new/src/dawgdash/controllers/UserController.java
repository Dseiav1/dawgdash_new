package dawgdash.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dawgdash.dbaccess.DBHelper;
import dawgdash.entities.Delivery;
import dawgdash.entities.Schedule;
import dawgdash.entities.User;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBHelper helper = new DBHelper();
		ServletContext ctx = this.getServletContext();
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		// -----------------------------------------
		// admin attempts to access scheduling page
		// admin.jsp
		// SCHEDULE
		// -----------------------------------------
		if (request.getParameter("task").equals("SCHEDULE")) {
			if (user != null && user.getRole().equals("admin")) {
				request.setAttribute("workers", helper.getWorkers());
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/scheduling.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("access_error", "access_error");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);

			}
		}

		// -----------------------------------------------------
		// admin attempts to access account administration page
		// admin.jsp
		// ADMINISTRATION
		// -----------------------------------------------------
		if (request.getParameter("task").equals("ADMINISTRATION")) {
			if (user != null && user.getRole().equals("admin")) {
				request.setAttribute("users", helper.getAllUsers());
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("access_error", "access_error");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}

		// --------------------------------------------------------------
		// user attempts to return to home page after submitting payment
		// confirmation.jsp
		// GO_HOME
		// --------------------------------------------------------------
		if (request.getParameter("task").equals("GO_HOME")) {
			if (user != null && user.getRole().equals("customer")) {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
			} else if (user != null && user.getRole().equals("worker")) {
				ArrayList<Delivery> pendingDeliveries = helper
						.getPendingCustomerDeliveries(user.getId());
				request.setAttribute("pending_deliveries", pendingDeliveries);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_pending_deliveries.jsp");
				dispatcher.forward(request, response);
			} else if (user != null && user.getRole().equals("admin")) {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		}

		// --------------------------------------------
		// user requests to access create account page
		// index.jsp
		// NEW_CUSTOMER_ACCOUNT
		// --------------------------------------------
		if (request.getParameter("task").equals("NEW_CUSTOMER_ACCOUNT")) {
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/create_account.jsp");
			dispatcher.forward(request, response);
		}

		// ------------------------------------------------
		// admin attempts to view individual schedule page
		// scheduling.jsp
		// INDIVIDUAL_SCHEDULE
		// ------------------------------------------------
		if (request.getParameter("task").equals("INDIVIDUAL_SCHEDULE")) {
			if (user != null && user.getRole().equals("admin")) {
				int workerId = Integer.parseInt(request
						.getParameter("worker_id"));
				Schedule schedule = helper.getSchedule(workerId);
				request.setAttribute("schedule", schedule);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/individual_schedule.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("access_error", "access_error");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}

		// --------------------------------------
		// customer requests modify account page
		// welcome.jsp
		// CUSTOMER_MODIFY_ACCOUNT
		// --------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_MODIFY_ACCOUNT")) {
			if (user.getRole().equals("customer")) {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("access_error", "access_error");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}

		// --------------------------------------------
		// worker attempts to view modify account page
		// worker_pending_deliveries.jsp
		// WORKER_MODIFY_ACCOUNT
		// --------------------------------------------
		if (request.getParameter("task").equals("WORKER_MODIFY_ACCOUNT")) {
			if (user.getRole().equals("worker")) {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("access_error", "access_error");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DBHelper helper = new DBHelper();
		ServletContext ctx = this.getServletContext();
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		// -------------------------------------------
		// admin attempts to change worker's password
		// account_administration.jsp
		// ADMIN_CHANGE_PASSWORD
		// -------------------------------------------
		if (request.getParameter("task").equals("ADMIN_CHANGE_PASSWORD")) {
			if (user == null || !user.getRole().equals("admin")) {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
			if (request.getParameter("account").equals("none")) {
				request.setAttribute("password_error",
						"Error: must select a user");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
				return;
			}
			int account = Integer.parseInt(request.getParameter("account"));
			String managerPassword = request.getParameter("manager_password");
			String newPassword = request.getParameter("new_password");
			String verifyNewPassword = request.getParameter("new_password");
			if (!helper.hash(managerPassword).equals(user.getPassword())) {
				System.out.println(user.getPassword());
				System.out.println(helper.hash(managerPassword));
				request.setAttribute("password_error",
						"Error: incorrect manager password");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (newPassword.length() < 8) {
				request.setAttribute("password_error",
						"Error: new password is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (!newPassword.equals(verifyNewPassword)) {
				request.setAttribute("password_error",
						"Error: provided passwords do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updatePassword(account, newPassword);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ------------------------------------
		// admin attempts to create new worker
		// account_administration.jsp
		// ADMIN_CREATE_USER
		// ------------------------------------
		if (request.getParameter("task").equals("ADMIN_CREATE_USER")) {
			String name = request.getParameter("name");
			System.out.println(name);
			String username = request.getParameter("username");
			System.out.println(username);
			String email = request.getParameter("email");
			System.out.println(email);
			String password = request.getParameter("password");
			System.out.println(password);
			String verifyPassword = request.getParameter("verify_password");
			System.out.println(verifyPassword);
			int transportation = Integer.parseInt(request
					.getParameter("transportation"));
			System.out.println(transportation);
			if (name.length() < 8) {
				request.setAttribute("new_worker_error",
						"Error: name field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (username.length() < 8) {
				request.setAttribute("new_worker_error",
						"Error: username field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (email.length() < 8) {
				request.setAttribute("new_worker_error",
						"Error: email address field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (!helper.availableUsername(username)) {
				request.setAttribute("new_worker_error",
						"Error: username already exists");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (!helper.availableEmail(email)) {
				request.setAttribute("new_worker_error",
						"Error: email address already exists");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (password.length() < 8) {
				request.setAttribute("new_worker_error",
						"Error: password field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (!password.equals(verifyPassword)) {
				request.setAttribute("new_worker_error",
						"Error: provided passwords do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else if (transportation == 0) {
				request.setAttribute("new_worker_error",
						"Error: must select available transportation");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/account_administration.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.createWorker(name, email, username, password,
						transportation);
				request.setAttribute("confirmation",
						"Account successfully created");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ---------------------------------------------
		// user attempts to create new customer account
		// create_account.jsp
		// CREATE_CUSTOMER_ACCOUNT
		// ---------------------------------------------
		if (request.getParameter("task").equals("CREATE_CUSTOMER_ACCOUNT")) {
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			String verifyPassword = request.getParameter("verify_password");
			if (name.length() < 8) {
				request.setAttribute("error", "Error: name field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (username.length() < 8) {
				request.setAttribute("error",
						"Error: username field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (email.length() < 8) {
				request.setAttribute("error",
						"Error: email address field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (!helper.availableUsername(username)) {
				request.setAttribute("error", "Error: username already exists");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (!helper.availableEmail(email)) {
				request.setAttribute("error",
						"Error: email address already exists");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (password.length() < 8) {
				request.setAttribute("error",
						"Error: password field is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (!password.equals(verifyPassword)) {
				request.setAttribute("error",
						"Error: provided passwords do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else if (address.length() == 0) {
				request.setAttribute("error",
						"Error: must provide a default address");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/create_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.createCustomer(name, email, username, password, address);
				request.setAttribute("confirmation",
						"Account successfully created");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		}

		// -------------------------------------
		// customer attempts to change password
		// customer_modify_account.jsp
		// CUSTOMER_CHANGE_PASSWORD
		// -------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_CHANGE_PASSWORD")) {
			String oldPassword = request.getParameter("old_password");
			String newPassword = request.getParameter("new_password");
			String verifyPassword = request.getParameter("verify_password");
			if (!helper.hash(oldPassword).equals(
					helper.getPassword(user.getId()))) {
				request.setAttribute("password_error",
						"Error: old password is incorrect");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!newPassword.equals(verifyPassword)) {
				request.setAttribute("password_error",
						"Error: provided passwords do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (newPassword.length() < 8) {
				request.setAttribute("password_error",
						"Error: new password is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updatePassword(user.getId(), newPassword);
				request.setAttribute("confirmation",
						"Password successfully changed");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ------------------------------------------
		// customer attempts to change email address
		// customer_modify_account.jsp
		// CUSTOMER_CHANGE_EMAIL
		// ------------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_CHANGE_EMAIL")) {
			String password = request.getParameter("password");
			String email = request.getParameter("new_email");
			String verifyEmail = request.getParameter("verify_email");
			if (!helper.hash(password).equals(user.getPassword())) {
				request.setAttribute("email_error",
						"Error: password is incorrect");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (email.length() < 8) {
				request.setAttribute("email_error", "Error: invalid email");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!helper.availableEmail(email)) {
				request.setAttribute("email_error",
						"Error: email address already exists");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!email.equals(verifyEmail)) {
				request.setAttribute("email_error",
						"Error: email addresses do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updateEmail(user.getId(), email);
				request.setAttribute("confirmation",
						"Email address successfully changed");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
			}
		}

		// --------------------------------------------
		// customer attempts to change default address
		// customer_modify_account.jsp
		// CUSTOMER_CHANGE_ADDRESS
		// --------------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_CHANGE_ADDRESS")) {
			String password = request.getParameter("password");
			String newAddress = request.getParameter("new_address");
			String verifyAddress = request.getParameter("verify_address");
			if (!helper.hash(password).equals(user.getPassword())) {
				request.setAttribute("address_error",
						"Error: password is incorrect");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (newAddress.length() == 0) {
				request.setAttribute("address_error", "Error: invalid address");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!newAddress.equals(verifyAddress)) {
				request.setAttribute("address_error",
						"Error: provided addresses do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updateAddress(user.getId(), newAddress);
				request.setAttribute("confirmation",
						"Default address successfully changed");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
			}
		}

		// -----------------------
		// User attempts to login
		// index.jsp
		// LOGIN
		// -----------------------
		if (request.getParameter("task").equals("LOGIN")) {
			String role = helper.attemptLogin(request.getParameter("username"),
					request.getParameter("password"));
			if (role.equals("login error")) {
				request.setAttribute("login_error",
						"Error: unable to authenticate login");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				int id = helper.getIdForUsername(request
						.getParameter("username"));
				user = helper.getUser(id);
				if (user == null) {
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
					return;
				}
				System.out.println(user.getRole());
				session.setAttribute("user", user);
				if (user.getRole().equals("customer")) {
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/welcome.jsp");
					dispatcher.forward(request, response);

				} else if (user.getRole().equals("worker")) {
					ArrayList<Delivery> pendingDeliveries = helper
							.getPendingWorkerDeliveries(id);
					request.setAttribute("pending_deliveries",
							pendingDeliveries);
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/worker_pending_deliveries.jsp");
					dispatcher.forward(request, response);
				} else if (user.getRole().equals("admin")) {
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/admin.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

		// -----------------------------------------
		// admin attempts to modify worker schedule
		// individual_schedule.jsp
		// CHANGE_WORKER_SCHEDULE
		// -----------------------------------------
		if (request.getParameter("task").equals("CHANGE_WORKER_SCHEDULE")) {
			if (user.getRole().equals("admin")) {
				int workerId = Integer.parseInt(request
						.getParameter("worker_id"));
				String sunday = request.getParameter("sunday_shift");
				String monday = request.getParameter("monday_shift");
				String tuesday = request.getParameter("tuesday_shift");
				String wednesday = request.getParameter("wednesday_shift");
				String thursday = request.getParameter("thursday_shift");
				String friday = request.getParameter("friday_shift");
				String saturday = request.getParameter("saturday_shift");
				String[] params = { sunday, monday, tuesday, wednesday,
						thursday, friday, saturday };
				int[] schedule = new int[14];
				for (int i = 0; i < 7; i++) {
					if (params[i].equals("none")) {
						request.setAttribute("error",
								"Error: please select a shift for each day");
						RequestDispatcher dispatcher = ctx
								.getRequestDispatcher("/individual_schedule.jsp");
						dispatcher.forward(request, response);
						return;
					}
					schedule[2 * i] = Integer.parseInt(params[i]
							.substring(0, 4));
					schedule[2 * i + 1] = Integer.parseInt(params[i].substring(
							5, 9));
				}
				helper.updateSchedule(workerId, schedule);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}

		}

		// -----------------------------------
		// worker attempts to change password
		// worker_modify_account.jsp
		// WORKER_CHANGE_PASSWORD
		// -----------------------------------
		if (request.getParameter("task").equals("WORKER_CHANGE_PASSWORD")) {
			String oldPassword = request.getParameter("old_password");
			String newPassword = request.getParameter("new_password");
			String verifyPassword = request.getParameter("verify_password");
			if (!helper.hash(oldPassword).equals(
					helper.getPassword(user.getId()))) {
				request.setAttribute("password_error",
						"Error: old password is incorrect");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!newPassword.equals(verifyPassword)) {
				request.setAttribute("password_error",
						"Error: provided passwords do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (newPassword.length() < 8) {
				request.setAttribute("password_error",
						"Error: new password is too short");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updatePassword(user.getId(), newPassword);
				request.setAttribute("confirmation",
						"Password successfully changed");
				ArrayList<Delivery> pendingDeliveries = helper
						.getPendingWorkerDeliveries(user.getId());
				request.setAttribute("pending_deliveries",
						pendingDeliveries);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_pending_deliveries.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ----------------------------------------
		// worker attempts to change email address
		// worker_modify_account.jsp
		// WORKER_CHANGE_EMAIL
		// ----------------------------------------
		if (request.getParameter("task").equals("WORKER_CHANGE_EMAIL")) {
			String password = request.getParameter("password");
			String email = request.getParameter("new_email");
			String verifyEmail = request.getParameter("verify_email");
			if (!helper.hash(password).equals(user.getPassword())) {
				request.setAttribute("email_error",
						"Error: password is incorrect");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (email.length() < 8) {
				request.setAttribute("email_error", "Error: invalid email");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!helper.availableEmail(email)) {
				request.setAttribute("email_error",
						"Error: email address already exists");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (!email.equals(verifyEmail)) {
				request.setAttribute("email_error",
						"Error: email addresses do not match");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updateEmail(user.getId(), email);
				request.setAttribute("confirmation",
						"Email address successfully changed");
				ArrayList<Delivery> pendingDeliveries = helper
						.getPendingWorkerDeliveries(user.getId());
				request.setAttribute("pending_deliveries",
						pendingDeliveries);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_pending_deliveries.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ----------------------------------------------
		// worker attempts to change transportation type
		// worker_modify_account.jsp
		// WORKER_CHANGE_VEHICLE
		// ----------------------------------------------
		if (request.getParameter("task").equals("WORKER_CHANGE_VEHICLE")) {
			String password = request.getParameter("password");
			int vehicle = Integer.parseInt(request.getParameter("vehicle"));
			if (!helper.hash(password).equals(user.getPassword())) {
				request.setAttribute("vehicle_error",
						"Error: password is incorrect");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else if (vehicle == 0) {
				request.setAttribute("vehicle_error",
						"Error: must select vehicle type");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_modify_account.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.updateTransportation(user.getId(), vehicle);
				request.setAttribute("confirmation",
						"Available vehicle changed");
				ArrayList<Delivery> pendingDeliveries = helper
						.getPendingWorkerDeliveries(user.getId());
				request.setAttribute("pending_deliveries",
						pendingDeliveries);
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/worker_pending_deliveries.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}