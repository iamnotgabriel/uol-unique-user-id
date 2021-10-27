package org.fatec.uniqueuserid;

import org.fatec.uniqueuserid.users.SignUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.service.ISignUpRepository;
import org.fatec.uniqueuserid.users.service.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
//@Transactional
@Rollback
class UniqueUserIdApplicationTests {
	@Autowired
	ISignUpRepository signUpRepository;

	@Autowired
	IUserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testSaveKeyUps() {

		User user = new User();
		user.setEmail("user@email.com");
		user.setName("Name");
		user.setPassword("123456");
		user.setPhone("129867623");
		userRepository.save(user);

		SignUp signUp = new SignUp();
		List<Integer> keyups = new ArrayList<>();
		keyups.add(0);
		keyups.add(1);
		keyups.add(2);
		signUp.setUser(user);
		signUp.setKeyUps(keyups);
		signUpRepository.save(signUp);

		SignUp sUp = signUpRepository.findById(signUp.getId()).get();
		System.out.println(sUp.getKeyUps());
	}

}
