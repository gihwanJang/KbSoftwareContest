package com.foch.springserver;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foch.springserver.model.user.User;
import com.foch.springserver.model.user.UserDao;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private UserDao userDao;

	// @Test
	void addUserTest() {
		User user = new User();
		user.setId("admin");
		user.setPw("0000");
		user.setName("admin");
		user.setAddress("internet");
		user.setTel("010-1234-5678");
		user.setType(3);
		userDao.addUser(user);
	}

	@Test
	void getAllUsers(){
		List<User> users = userDao.getAllUser();
		System.out.println(users);
	}
}
