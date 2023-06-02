package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUserByUserName(String userName);

    public void deleteUserByUserName(String userName);

    public void deleteUserByUserId(Long userId);

    User updateUserByUserName(String userName, User user) throws Exception;

    User updatePasswordByUserName(String userName, String password) throws Exception;

    User deactivateUserByUserName(String userName);
}
