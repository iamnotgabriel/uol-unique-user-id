package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.UserCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public User create(UserCreationDTO userDTO) {
        User newUser = User.create(userDTO);
        return userRepository.save(newUser);
    }
}
