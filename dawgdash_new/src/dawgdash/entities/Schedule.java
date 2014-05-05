package dawgdash.entities;

public class Schedule {
	private int workerID;
	private String name;
	int sundayStart;
	int sundayEnd;
	int mondayStart;
	int mondayEnd;
	int tuesdayStart;
	int tuesdayEnd;
	int wednesdayStart;
	int wednesdayEnd;
	int thursdayStart;
	int thursdayEnd;
	int fridayStart;
	int fridayEnd;
	int saturdayStart;
	int saturdayEnd;
	
	public Schedule(int workerID, String name, int sundayStart, int sundayEnd,
			int mondayStart, int mondayEnd, int tuesdayStart, int tuesdayEnd,
			int wednesdayStart, int wednesdayEnd, int thursdayStart,
			int thursdayEnd, int fridayStart, int fridayEnd, int saturdayStart,
			int saturdayEnd) {
		this.workerID = workerID;
		this.name = name;
		this.sundayStart = sundayStart;
		this.sundayEnd = sundayEnd;
		this.mondayStart = mondayStart;
		this.mondayEnd = mondayEnd;
		this.tuesdayStart = tuesdayStart;
		this.tuesdayEnd = tuesdayEnd;
		this.wednesdayStart = wednesdayStart;
		this.wednesdayEnd = wednesdayEnd;
		this.thursdayStart = thursdayStart;
		this.thursdayEnd = thursdayEnd;
		this.fridayStart = fridayStart;
		this.fridayEnd = fridayEnd;
		this.saturdayStart = saturdayStart;
		this.saturdayEnd = saturdayEnd;
	}

	public int getWorkerID() {
		return workerID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSunday() {
		if(sundayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", sundayStart);
			String end = String.format("%04d", sundayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}
	
	public String getMonday() {
		if(mondayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", mondayStart);
			String end = String.format("%04d", mondayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}
	
	public String getTuesday() {
		if(tuesdayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", tuesdayStart);
			String end = String.format("%04d", tuesdayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}
	
	public String getWednesday() {
		if(wednesdayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", wednesdayStart);
			String end = String.format("%04d", wednesdayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}
	
	public String getThursday() {
		if(thursdayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", thursdayStart);
			String end = String.format("%04d", thursdayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}
	
	public String getFriday() {
		if(fridayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", fridayStart);
			String end = String.format("%04d", fridayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}
	
	public String getSaturday() {
		if(saturdayStart == 0) {
			return "off";
		}
		else {
			String retVal = "";
			String start = String.format("%04d", saturdayStart);
			String end = String.format("%04d", saturdayEnd);
			if(Integer.parseInt(start) == 1200) {
				retVal += "12:00 pm - ";
			} else if(Integer.parseInt(start) < 1200) {
				retVal += Integer.parseInt(start) / 100 + ":00 am - ";
			} else {
				retVal += Integer.parseInt(start) / 100 % 12 + ":00 pm - ";
			}
			if(Integer.parseInt(end) == 1200) {
				retVal += "12:00 pm";
			} else if(Integer.parseInt(end) < 1200) {
				retVal += Integer.parseInt(end) / 100 + ":00 am";
			} else {
				retVal += Integer.parseInt(end) / 100 % 12 + ":00 pm";
			}
			return retVal;
		}
		
	}

	


}