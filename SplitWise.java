package splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class SplitWise implements Modify, Display {

	private static int user_id = 0;
	private static int group_id = 0;
	private static int bill_id = 0;

	User u = new User();
	Group g = new Group();
	Bill b = new Bill();
	static HashMap<Integer, User> id_user_map = new HashMap<>();

	static HashMap<Integer, Double> user_amount_map = new HashMap<>();
	static HashMap<Integer, Group> id_group_name_map = new HashMap<>();
	static HashMap<Integer, HashSet<User>> user_group_map = new HashMap<>();
	static HashMap<Integer, Double> group_amount_map = new HashMap<>();

	@Override
	public String add_user(User new_user) {
		if (!check_if_user_exist(new_user)) {
			id_user_map.put(++user_id, new_user);
			user_amount_map.put(user_id, (double) 0);
			return "User added Successfully";
		} else {
//			with name = " + new_user.getName() + " and Mobile Number = "
//					+ new_user.getMobileNumber() + "
			return "User already exist";
		}
	}

	private boolean check_if_user_exist(User new_user) {

		return id_user_map.containsValue(new_user);
	}

	@Override
	public void add_group(User created_by, Group new_group) {
		if (!check_if_group_exist(created_by, new_group)) {
			int number_of_users = 0;
			Scanner sc = new Scanner(System.in);
			while (number_of_users == 0) {
				System.out.println("How Many users you want to add to " + new_group.getGroup_name()
						+ " Note: number must be greater than 0");
				number_of_users = Integer.parseInt(sc.next());
			}
			id_group_name_map.put(++group_id, new_group);
			user_group_map.put(group_id, new HashSet<>());
			group_amount_map.put(group_id, 0.00);
			user_group_map.get(group_id).add(created_by);
			for (int i = 0; i < number_of_users; i++) {
				String name = sc.nextLine();
				String mobile_number = sc.nextLine();
				if (id_user_map.containsValue(new User(name, mobile_number))) {
					add_user_to_group(created_by, new_group, new User(name, mobile_number));
				}
			}
			sc.close();

		} else {
			System.out.println("Group with name = " + new_group.getGroup_name() + " already exist");
		}
	}

	private boolean check_if_group_exist(User created_by, Group new_group) {
		ArrayList<Integer> group_with_same_name_list = new ArrayList<>();
		for (Integer key : id_group_name_map.keySet()) {
			if (id_group_name_map.get(key).getGroup_name() != new_group.getGroup_name()) {
				return false;
			} else {

				group_with_same_name_list.add(key);
			}
		}
		for (int i = 0; i < group_with_same_name_list.size(); i++) {
			if (user_group_map.get(group_with_same_name_list.get(i)).contains(created_by)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void add_user_to_group(User added_by, Group group_name, User user_name_to_add) {
		int group_id = getGroupId(group_name);
		if (group_id != -1 && user_group_map.get(group_id).contains(added_by)) {
			user_group_map.get(group_id).add(user_name_to_add);
		} else {
			System.out
					.print("Sorry User " + added_by.getName() + " can't add to group = " + group_name.getGroup_name());
			return;
		}
	}

	private Integer getGroupId(Group group_name) {
		for (Integer key : id_group_name_map.keySet()) {
			if (id_group_name_map.get(key).getGroup_name().equals(group_name.getGroup_name())) {
				return key;
			}
		}
		return -1;
	}

	@Override
	public void add_bill_to_users(User added_by, ArrayList<User> users_in_bill, Bill new_bill) {
		double amount = new_bill.getAmount();
		double individual_amount = amount / (users_in_bill.size() + 1);
		individual_amount = (double) Math.round(individual_amount * 100) / 100;
		amount = amount - individual_amount;
		individual_amount = amount / (users_in_bill.size());
		individual_amount = (double) Math.round(individual_amount * 100) / 100;
		ArrayList<Integer> user_ids = new ArrayList<>();
		int i = 0;
		for (Integer keys : id_user_map.keySet()) {
			if (id_user_map.containsValue(users_in_bill.get(i))) {
				user_ids.add(keys);
			} else {
				System.out.println(
						"Bill can't be added as user with name = " + users_in_bill.get(i).getName() + "does not exist");
				user_ids.clear();
				return;
			}
		}
		for (i = 0; i < user_ids.size(); i++) {
			double existing_amount = user_amount_map.get(user_ids.get(i)) + individual_amount;
			user_amount_map.put(user_ids.get(i), existing_amount);
		}
		b.id_bill_map.put(++bill_id, new_bill);
		b.bill_object_map.put(bill_id, users_in_bill);
	}

	@Override
	public void add_bill_to_group(User added_by, Group group_name, Bill new_bill) {
		int group_id = getGroupId(group_name);
		if (user_group_map.get(group_id).contains(added_by)) {
			double amount = new_bill.getAmount();
			double individual_amount = amount / (user_group_map.get(group_id).size() + 1);
			individual_amount = (double) Math.round(individual_amount * 100) / 100;
			amount = amount - individual_amount;
			individual_amount = amount / (user_group_map.get(group_id).size());
			individual_amount = (double) Math.round(individual_amount * 100) / 100;
		} else {
			System.out.print(
					"Sorry User " + added_by.getName() + " can't add Bill to group = " + group_name.getGroup_name());
			return;
		}
	}

	@Override
	public void show_user_amout(User user) {
		for (Integer key : id_user_map.keySet()) {
			if (id_user_map.get(key) == user) {
				System.out.println("Pending amount for " + user.getName() + " is " + user_amount_map.get(key));
			}
		}
	}

	@Override
	public void show_group_amount(Group group_name) {
		for (Integer key : id_group_name_map.keySet()) {
			if (id_group_name_map.get(key) == group_name) {
				System.out.println(
						"Pending amount for " + group_name.getGroup_name() + " is " + group_amount_map.get(key));
			}
		}
	}

	@Override
	public void show_user_in_group_amount(Group group_name) {
		for (Integer key : id_group_name_map.keySet()) {
			if (id_group_name_map.get(key) == group_name) {
				HashSet<User> user_set = user_group_map.get(key);
				for (User u : user_set) {
					show_user_amout(u);
				}
			}
		}

	}

	@Override
	public void show_all_users() {
		for (Integer key : id_user_map.keySet()) {
			System.out.println("User name = " + id_user_map.get(key));
		}
	}

	@Override
	public void show_all_groups() {
		for (Integer key : id_group_name_map.keySet()) {
			System.out.println("Group name = " + id_group_name_map.get(key));
		}

	}

}
