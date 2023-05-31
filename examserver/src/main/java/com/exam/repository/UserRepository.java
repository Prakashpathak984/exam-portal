package com.exam.repository;

import com.exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByUserName(String userName);

    public User findUserByEmail(String email);

    public void deleteUserByUserName(String userName);
}
