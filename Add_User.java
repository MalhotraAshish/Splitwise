package splitwise;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class Add_User {
	SplitWise sp = new SplitWise();
	User u = new User();
	Group g = new Group();

	@Test
	// @DisplayName("User added Successfully")
	void testUserAdded() {
		assertEquals(sp.add_user(new User("Ashish Malhotra", "8006066680")), "User added Successfully");
	}

	@Test
	// @DisplayName("User already exist")
	void testUserAlreadyExist() {
		assertEquals(new User("Ashish Malhotra", "8006066680"), "User already exist");
	}

}
