package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.SingUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISingUpRepository extends JpaRepository<SingUp, Integer> {
    SingUp findByUserId(Integer userId);
}
