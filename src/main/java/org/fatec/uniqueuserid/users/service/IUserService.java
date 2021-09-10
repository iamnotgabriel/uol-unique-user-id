package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;

public interface IUserService {
    User create(UserCreationDTO userDTO) throws Exception;
}
