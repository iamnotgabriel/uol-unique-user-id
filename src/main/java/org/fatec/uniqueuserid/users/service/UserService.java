package org.fatec.uniqueuserid.users.service;

import java.util.List;

import javax.transaction.Transactional;

import org.fatec.uniqueuserid.users.SignUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISignUpRepository signUpRepository;

    @Autowired
    UserCreationValidator userCreationValidator;

    @Transactional
    public User create(UserCreationDTO userDTO) throws Exception {
        userCreationValidator.validate(userDTO);
        User newUser = User.create(userDTO);
        newUser = userRepository.save(newUser);
        System.out.println(userDTO.deviceId);
        SignUp signUp = SignUp.create(userDTO, newUser);
        signUpRepository.save(signUp);
        return newUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "signUp.deviceId"));
    }
}
