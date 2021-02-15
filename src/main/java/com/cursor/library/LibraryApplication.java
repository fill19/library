package com.cursor.library;

import com.cursor.library.daos.UserDao;
import com.cursor.library.daos.UserPermission;
import com.cursor.library.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
public class LibraryApplication {
	private final UserDao userDao;
	private final BCryptPasswordEncoder encoder;

	@Autowired
	public LibraryApplication(UserDao userDao, BCryptPasswordEncoder encoder) {
		this.userDao = userDao;
		this.encoder = encoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@PostConstruct
	public void addUsers() {
		var user1 = new Users();
		user1.setUserName("Nazar");
		user1.setPassword(encoder.encode("jacks"));
		user1.setPermissions(Set.of(UserPermission.ROLE_READ, UserPermission.ROLE_WRITE));
		userDao.save(user1);
		var user2 = new Users();
		user2.setUserName("Frank");
		user2.setPassword(encoder.encode("dai"));
		user2.setPermissions(Set.of(UserPermission.ROLE_ADMIN));
		userDao.save(user2);
	}
}
