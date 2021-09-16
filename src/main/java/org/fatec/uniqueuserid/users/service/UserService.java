package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.SignUp;
import org.fatec.uniqueuserid.users.User;
import org.fatec.uniqueuserid.users.controller.dto.UserCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        SignUp signUp = SignUp.create(userDTO, newUser);
        signUpRepository.save(signUp);
        return newUser;
    }

    @Override
    public List<SignUp> findAll() {
        return signUpRepository.findAll();
    }
}
