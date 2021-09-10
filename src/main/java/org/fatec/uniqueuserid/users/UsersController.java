package org.fatec.uniqueuserid.users;

import org.fatec.uniqueuserid.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsersController {

    @Autowired
    IUserService userService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody() UserCreationDTO userDTO) {
        try {
            User user = userService.create(userDTO);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
