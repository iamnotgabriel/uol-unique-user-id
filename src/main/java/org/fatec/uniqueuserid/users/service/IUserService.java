package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.UserCreationDTO;

public interface IUserService {
    User create(UserCreationDTO userDTO) throws Exception;
}
