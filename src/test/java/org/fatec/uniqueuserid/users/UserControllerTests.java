package org.fatec.uniqueuserid.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class UserControllerTests {
    @Autowired
    UsersController usersController;

    @Test
    void contextLoads() {}

    UserCreationDTO getUserDTO() {
        UserCreationDTO dto = new UserCreationDTO();
        dto.name = "Spike Spiegel";
        dto.email = "spike@bebop.space";
        dto.password = "where_is_julia";
        dto.phone = "998887777";
        return dto;
    }

    @Test
    void testCreateUserSuccess() {
        UserCreationDTO dto = getUserDTO();
        ResponseEntity<User> res = usersController.createUser(dto);
        UserAsserts.equalsDTO(dto, res.getBody());
    }


}
