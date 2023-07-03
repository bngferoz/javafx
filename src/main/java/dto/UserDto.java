package dto;

public class UserDto {
	private String email;
	private String first_name;
	private String last_name;
	private String address;
	
	
	public String getAddress() {
		return address;
	}

	

	public UserDto() {}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDto(String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setAddress(String string) {
		this.address = string;
	}



	@Override
	public String toString() {
		return "UserDto [email=" + email + ", first_name=" + first_name + ", last_name=" + last_name + ", address="
				+ address + "]";
	}
	
}
