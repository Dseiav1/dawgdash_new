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
 * Servlet implementation class DeliveryController
 */
public class DeliveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeliveryController() {
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

		// --------------------------------
		// user requests checkout page
		// customer_pending_deliveries.jsp
		// CHECKOUT
		// --------------------------------
		if (request.getParameter("task").equals("CHECKOUT")) {
			ArrayList<Delivery> pendingDeliveries = helper
					.getPendingCustomerDeliveries(user.getId());
			int priceInt = 0;
			for (Delivery delivery : pendingDeliveries) {
				priceInt += delivery.getPriceInt();
			}
			String price = "$" + priceInt + ".00";
			request.setAttribute("price", price);
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/checkout.jsp");
			dispatcher.forward(request, response);
		}

		// --------------------------
		// user requests an estimate
		// index.jsp
		// ESTIMATE
		// --------------------------
		if (request.getParameter("task").equals("ESTIMATE")) {
			try {
				if (request.getParameter("quantity").length() == 0
						|| request.getParameter("size").equals("none")) {
					throw new Exception();
				} else {
					int quantity = Integer.parseInt(request
							.getParameter("quantity"));
					int price = -1;
					if (request.getParameter("size").equals("letter")) {
						price = 5;
					} else if (request.getParameter("size").equals("small")) {
						price = 5 + quantity;
					} else {
						price = 5 + 2 * quantity;
					}
					String priceString = "$" + price + ".00";
					request.setAttribute("estimate", priceString);
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("estimate_error",
						"Please make sure you complete all fields properly");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ----------------------------------------------
		// customer attempts to view a previous delivery
		// previous_deliveries.jsp
		// CUSTOMER_PAST_DELIVERY
		// ----------------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_PAST_DELIVERY")) {
			try {
				int deliveryId = Integer.parseInt(request
						.getParameter("delivery_id"));
				Delivery delivery = helper.getDelivery(deliveryId);
				if (delivery.getCustomerId() == user.getId()) {
					request.setAttribute("delivery", delivery);
				}
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_delivery.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/customer_delivery.jsp");
				dispatcher.forward(request, response);
			}
		}

		// -----------------------------------------
		// customer requests schedule delivery page
		// welcome.jsp
		// NEW_DELIVERY
		// -----------------------------------------
		if (request.getParameter("task").equals("NEW_DELIVERY")) {
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/schedule_delivery.jsp");
			dispatcher.forward(request, response);
		}

		// ------------------------------------------
		// customer requests pending deliveries page
		// welcome.jsp
		// CUSTOMER_PENDING_DELIVERIES
		// ------------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_PENDING_DELIVERIES")) {
			ArrayList<Delivery> deliveries = helper
					.getPendingCustomerDeliveries(user.getId());
			request.setAttribute("pending_deliveries", deliveries);
			int priceInt = 0;
			for (Delivery delivery : deliveries) {
				priceInt += delivery.getPriceInt();
			}
			request.setAttribute("price", "$" + priceInt + ".00");
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/customer_pending_deliveries.jsp");
			dispatcher.forward(request, response);
		}

		// ---------------------------------------
		// customer requests past deliveries page
		// welcome.jsp
		// CUSTOMER_PAST_DELIVERIES
		// ---------------------------------------
		if (request.getParameter("task").equals("CUSTOMER_PAST_DELIVERIES")) {
			ArrayList<Delivery> previousDeliveries = helper
					.getPreviousDeliveries(user.getId());
			request.setAttribute("past_deliveries", previousDeliveries);
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/previous_deliveries.jsp");
			dispatcher.forward(request, response);

		}

		// ---------------------------------
		// worker views individual delivery
		// worker_pending_deliveries.jsp
		// VIEW_DELIVERY
		// ---------------------------------
		if (request.getParameter("task").equals("VIEW_DELIVERY")) {
			int deliveryId = Integer.parseInt(request
					.getParameter("delivery_id"));
			Delivery delivery = helper.getDelivery(deliveryId);
			if (user.getId() == delivery.getWorkerId()) {
				request.setAttribute("delivery", delivery);
			}
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/worker_delivery.jsp");
			dispatcher.forward(request, response);
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

		// -------------------------
		// customer submits payment
		// checkout.jsp
		// SUBMIT_PAYMENT
		// -------------------------
		if (request.getParameter("task").equals("SUBMIT_PAYMENT")) {
			String ccNumber = request.getParameter("credit_card");
			String billingAddress = request.getParameter("billing_address");
			ArrayList<Delivery> pendingDeliveries = helper
					.getPendingCustomerDeliveries(user.getId());
			int priceInt = 0;
			for (Delivery delivery : pendingDeliveries) {
				priceInt += delivery.getPriceInt();
			}
			String price = "$" + priceInt + ".00";
			request.setAttribute("price", price);
			if ((ccNumber.length() != 15 && ccNumber.length() != 16)
					|| !ccNumber.matches("[0-9]+")) {
				request.setAttribute("error",
						"Error: invalid credit card number");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/checkout.jsp");
				dispatcher.forward(request, response);

			} else if (billingAddress.length() == 0) {
				request.setAttribute("error", "Error: invalid billing address");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/checkout.jsp");
				dispatcher.forward(request, response);
			} else {
				for (Delivery delivery : pendingDeliveries) {
					helper.assignDelivery(delivery.getId());
				}
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/confirmation.jsp");
				dispatcher.forward(request, response);

			}
		}

		// ---------------------------------------------
		// customer attempts to cancel pending delivery
		// customer_pending_deliveries.jsp
		// CANCEL_DELIVERY
		// ---------------------------------------------
		if (request.getParameter("task").equals("CANCEL_DELIVERY")) {
			int deliveryId = Integer.parseInt(request
					.getParameter("delivery_id"));
			Delivery delivery = helper.getDelivery(deliveryId);
			if (delivery.getCustomerId() != user.getId()) {
				request.setAttribute("error",
						"Error: no such delivery or not authorized");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			} else {
				helper.cancelDelivery(deliveryId);
				request.setAttribute("confirmation",
						"Delivery successfully canceled");
				ArrayList<Delivery> pendingDeliveries = helper
						.getPendingCustomerDeliveries(user.getId());
				if (pendingDeliveries.isEmpty()) {
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/welcome.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("pending_deliveries",
							pendingDeliveries);
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/customer_pending_deliveries.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

		// -----------------------------------------
		// customer attempts to schedule a delivery
		// schedule_delivery.jsp
		// SCHEDULE_DELIVERY
		// -----------------------------------------
		if (request.getParameter("task").equals("SCHEDULE_DELIVERY")) {
			try {
				int id = user.getId();
				String pickupAddress = request.getParameter("pickup_address");
				if (pickupAddress.length() == 0) {
					pickupAddress = helper.getDefaultAddress(id);
				}
				String destinationAddress = request
						.getParameter("destination_address");
				int quantity = Integer.parseInt(request
						.getParameter("quantity"));
				int transportation = Integer.parseInt(request
						.getParameter("size"));
				String description = request.getParameter("description");
				String instructions = request.getParameter("instructions");
				if (instructions.length() == 0) {
					instructions = "none";
				}
				int pickupTime = Integer.parseInt(request.getParameter("time"));
				if (pickupAddress.length() < 8) {
					request.setAttribute("error",
							"Error: invalid pickup address");
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/schedule_delivery.jsp");
					dispatcher.forward(request, response);
				} else if (destinationAddress.length() < 8) {
					request.setAttribute("error",
							"Error: invalid destination address");
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/schedule_delivery.jsp");
					dispatcher.forward(request, response);
				} else if (description.length() == 0) {
					request.setAttribute("error",
							"Error: must provide description");
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/schedule_delivery.jsp");
					dispatcher.forward(request, response);
				} else if (quantity < 1 || quantity > 10) {
					request.setAttribute("error", "Error: invalid quantity");
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/schedule_delivery.jsp");
					dispatcher.forward(request, response);
				} else if (transportation == 0) {
					request.setAttribute("error",
							"Error: must select delivery size");
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/schedule_delivery.jsp");
					dispatcher.forward(request, response);
				} else {
					helper.scheduleDelivery(id, pickupAddress,
							destinationAddress, quantity, transportation,
							description, instructions, pickupTime);
					RequestDispatcher dispatcher = ctx
							.getRequestDispatcher("/welcome.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				request.setAttribute("error",
						"Unable to schedule delivery, please ensure all fields completed properly");
				RequestDispatcher dispatcher = ctx
						.getRequestDispatcher("/schedule_delivery.jsp");
				dispatcher.forward(request, response);
			}
		}

		// ---------------------------
		// worker closes a delivery
		// worker_delivery.jsp
		// WORKER_CLOSE_DELIVERY
		// ---------------------------
		if (request.getParameter("task").equals("WORKER_CLOSE_DELIVERY")) {
			String workerComments = request.getParameter("comments");
			if(workerComments.length() == 0) {
				workerComments = "none";
			}
			int deliveryId = Integer.parseInt(request
					.getParameter("delivery_id"));
			helper.completeDelivery(deliveryId, user.getId(), workerComments);
			ArrayList<Delivery> pendingDeliveries = helper
					.getPendingWorkerDeliveries(user.getId());
			request.setAttribute("pending_deliveries", pendingDeliveries);
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/worker_pending_deliveries.jsp");
			dispatcher.forward(request, response);
		}

		// ---------------------------------------
		// worker marks delivery as uncompletable
		// worker_delivery.jsp
		// UNCOMPLETABLE
		// ---------------------------------------
		if (request.getParameter("task").equals("UNCOMPLETABLE")) {
			int deliveryId = Integer.parseInt(request
					.getParameter("delivery_id"));
			helper.markUncompletable(deliveryId, user.getId());
			ArrayList<Delivery> pendingDeliveries = helper
					.getPendingWorkerDeliveries(user.getId());
			request.setAttribute("pending_deliveries", pendingDeliveries);
			RequestDispatcher dispatcher = ctx
					.getRequestDispatcher("/worker_pending_deliveries.jsp");
			dispatcher.forward(request, response);
		}

	}

}