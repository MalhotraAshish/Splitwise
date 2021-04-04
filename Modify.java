package splitwise;

import java.util.ArrayList;

public interface Modify {

	public String add_user(User new_user);

	public void add_group(User created_by, Group new_group);

	public void add_user_to_group(User added_by, Group group_name, User user_name_to_add);

	public void add_bill_to_users(User added_by, ArrayList<User> users_in_bill, Bill amount);

	public void add_bill_to_group(User added_by, Group group_name, Bill amount);
}
