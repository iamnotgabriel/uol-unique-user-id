package org.fatec.uniqueuserid.users.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;
import org.fatec.uniqueuserid.errors.ApiError;
import org.fatec.uniqueuserid.users.SignUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.fatec.uniqueuserid.users.controller.dto.UserDTO;
import org.fatec.uniqueuserid.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsersController {

    @Autowired
    IUserService userService;

    @GetMapping(value = "/health", produces = "application/json")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Object> createUser(@RequestBody() UserCreationDTO userDTO) {
        try {
            User user = userService.create(userDTO);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception err) {
            ApiError error = new ApiError(HttpStatus.BAD_REQUEST, err.getMessage());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "", produces = "application/json")
    @JsonView(User.UserData.class)
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();

        return new ResponseEntity(users, HttpStatus.OK);
    }

    private UserDTO toUserDTO(SignUp signup) {
        UserDTO dto = new UserDTO();
        dto.deviceId = signup.getDeviceId();
        dto.name = signup.getUser().getName();
        dto.email = signup.getUser().getEmail();
        dto.id = signup.getUser().getId();

        return dto;
    }

}
