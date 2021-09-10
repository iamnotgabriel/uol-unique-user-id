package org.fatec.uniqueuserid.users.service;

import org.fatec.uniqueuserid.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
