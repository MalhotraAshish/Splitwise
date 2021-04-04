package splitwise;

public class User {

	String name;
	String mobileNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean equals(User new_user) {
		// TODO Auto-generated method stub
		return this.name.equals(new_user.name) && this.mobileNumber.equals(new_user.mobileNumber);
	}

	@Override
	public int hashCode() {
		int prime = (1000) * (1000) * (1000) + 7;
		int result = 1;
		result = prime * result + name != null ? name.hashCode() : 0;
		return result;
	}

	public User(String name, String mobileNumber) {
		this.name = name;
		this.mobileNumber = mobileNumber;
	}

	public User() {
	}

}
