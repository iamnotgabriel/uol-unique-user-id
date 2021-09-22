package org.fatec.uniqueuserid.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.coyote.Response;
import org.fatec.uniqueuserid.errors.ApiError;
import org.fatec.uniqueuserid.users.controller.UsersController;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.fatec.uniqueuserid.users.controller.dto.UserDTO;
import org.fatec.uniqueuserid.users.service.ISignUpRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback
public class UserControllerTests {
    @Autowired
    UsersController usersController;

    @Autowired
    ISignUpRepository signUpRepository;

    @Test
    void contextLoads() {
    }

    UserCreationDTO getUserDTO() {
        UserCreationDTO dto = new UserCreationDTO();
        dto.name = "Spike Spiegel";
        dto.email = "spike@bebop.space";
        dto.password = "where_is_julia";
        dto.phone = "998887777";
        dto.deviceId = "b4008911";
        dto.startTime = new Timestamp(0L);
        dto.endTime = new Timestamp(System.currentTimeMillis());
        dto.pasteCount = 1;
        return dto;
    }

    @Test
    void testCreateUserSuccess() {
        UserCreationDTO dto = getUserDTO();

        ResponseEntity<Object> res = usersController.createUser(dto);

        List<SignUp> singUp = signUpRepository.findAll();
        assertTrue(singUp.size() > 0);
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

    @Test
    void testGetAllUsers() {
        UserCreationDTO dto = getUserDTO();
        usersController.createUser(dto);

        dto.email = "other@email.com";
        dto.name = "Other Name";
        dto.deviceId = "h1a2l32";
        usersController.createUser(dto);

        ResponseEntity<List<User>> response = usersController.findAll();
        List<User> users = response.getBody();

        assertEquals(users.size(), 2);

        assertEquals(users.get(0).getName(), "Spike Spiegel");
        assertEquals(users.get(0).getEmail(), "spike@bebop.space");
        assertEquals(users.get(0).getSignUp().getDeviceId(), "b4008911");

        assertEquals(users.get(1).getEmail(), "Other Name");
        assertEquals(users.get(1).getEmail(), "other@email.com");
        assertEquals(users.get(1).getSignUp().getDeviceId(), "h1a2l32");
    }

    @Test
    void testGetEmptyUserList() {
        ResponseEntity<List<User>> response = usersController.findAll();
        List<User> users = response.getBody();

        assertEquals(users.size(), 0);
    }

}
