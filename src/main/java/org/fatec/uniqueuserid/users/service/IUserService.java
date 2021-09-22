package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.SignUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.fatec.uniqueuserid.users.controller.dto.UserDTO;

import java.util.List;

public interface IUserService {
    User create(UserCreationDTO userDTO) throws Exception;
    List<User> findAll();
}
