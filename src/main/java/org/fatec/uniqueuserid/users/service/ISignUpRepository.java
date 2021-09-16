package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISignUpRepository extends JpaRepository<SignUp, Integer> {
    SignUp findByUserId(Integer userId);
}
