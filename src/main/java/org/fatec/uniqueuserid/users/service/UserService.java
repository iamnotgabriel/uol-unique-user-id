package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.SingUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISingUpRepository singUpRepository;

    @Autowired
    UserCreationValidator userCreationValidator;

    @Transactional
    public User create(UserCreationDTO userDTO) throws Exception {
        userCreationValidator.validate(userDTO);
        User newUser = User.create(userDTO);
        newUser = userRepository.save(newUser);
        SingUp signUp = SingUp.create(userDTO, newUser);
        singUpRepository.save(signUp);
        return newUser;
    }
}
