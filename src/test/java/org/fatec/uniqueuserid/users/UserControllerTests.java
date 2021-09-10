package org.fatec.uniqueuserid.users;

import org.fatec.uniqueuserid.errors.ApiError;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.fatec.uniqueuserid.users.controller.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
        ResponseEntity<Object> res = usersController.createUser(dto);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        UserAsserts.equalsDTO(dto, (User) res.getBody());
    }

    @Test
    void testCreateUserFailsWithNullField() {
        UserCreationDTO dto = getUserDTO();
        dto.email = null;
        ResponseEntity<Object> res = usersController.createUser(dto);
        ApiError error = (ApiError) res.getBody();
        assertEquals(res.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals("Missing user.email", error.message);
    }

    @Test
    void testCreateUserFailsWithSameEmail() {
        UserCreationDTO dto = getUserDTO();
        dto.email = "spike@red.dragon";
        ResponseEntity<Object> ok = usersController.createUser(dto);
        ResponseEntity<Object> fails = usersController.createUser(dto);
        assertEquals(HttpStatus.OK, ok.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, fails.getStatusCode());
    }

}
