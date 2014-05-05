package dawgdash.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Delivery {

	private int id;
	private int customerId;
	private int workerId;
	private Timestamp date;
	private int pickupTime;
	private String pickupAddress;
	private String destinationAddress;
	private String instructions;
	private String description;
	private String workerComments;
	private String status;
	private int price;
	private int quantity;
	private int transportation;

	public Delivery(int id, int customerId, int workerId, Timestamp date,
			int pickupTime, String pickupAddress, String destinationAddress,
			String instructions, String description, String workerComments,
			String status, int price, int quantity, int transportation) {
		this.id = id;
		this.customerId = customerId;
		this.workerId = workerId;
		this.date = date;
		this.pickupTime = pickupTime;
		this.pickupAddress = pickupAddress;
		this.destinationAddress = destinationAddress;
		this.instructions = instructions;
		this.description = description;
		this.workerComments = workerComments;
		this.status = status;
		this.price = price;
		this.quantity = quantity;
		this.transportation = transportation;
	}

	public int getId() {
		return id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getWorkerId() {
		return workerId;
	}

	public String getDate() {
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}

	public String getPickupTime() {
		String time = String.format("%04d", pickupTime);
		int start = Integer.parseInt(time.substring(0, 2));
		String end = time.substring(2, 4);
		if (start == 12) {
			return start + ":" + end + " pm";
		} else if (start > 12) {
			return (start % 12) + ":" + end + " pm";
		} else {
			return start + ":" + end + " am";
		}
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public String getInstructions() {
		return instructions;
	}

	public String getDescription() {
		return description;
	}

	public String getWorkerComments() {
		return workerComments;
	}

	public String getStatus() {
		return status;
	}

	public String getPrice() {
		return "$" + price + ".00";
	}

	public int getPriceInt() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getTransportation() {
		return transportation;
	}

}