package org.fatec.uniqueuserid;

import javax.transaction.Transactional;
import org.springframework.test.annotation.Rollback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
@Rollback
class UniqueUserIdApplicationTests {

	@Test
	void contextLoads() {
	}

}
