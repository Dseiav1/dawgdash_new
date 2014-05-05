package dawgdash.entities;

public class User {

	private String name;
	private String email;
	private String username;
	private String password;
	private String defaultAddress;
	private int transportation;
	private String role;

	public User(int id, String name, String email, String username,
			String password, String defaultAddress, int transportation,
			String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.defaultAddress = defaultAddress;
		this.transportation = transportation;
		this.role = role;
	}

	private int id;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public int getTransportation() {
		return transportation;
	}

	public String getRole() {
		return role;
	}

}
