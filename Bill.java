package splitwise;

import java.util.ArrayList;
import java.util.HashMap;

public class Bill {

	int id;
	String title;
	String info;
	double amount;
	HashMap<Integer, Bill> id_bill_map = new HashMap<>();
	HashMap<Integer, ArrayList<User>> bill_object_map = new HashMap<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
