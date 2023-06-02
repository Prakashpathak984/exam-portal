package com.exam.service.implementation;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findUserByUserName(user.getUserName());

        if(local != null ){
            System.out.println("Username already exists !!");
            throw new Exception("Duplicate username");
        }
        else{
            local = this.userRepository.findUserByEmail(user.getEmail());

            if(local != null){
                System.out.println("Email used already !!");
                throw new Exception("Duplicate email");
            }
            else{
                for(UserRole ur:userRoles){
                    this.roleRepository.save(ur.getRole());
                }

                user.getUserRoles().addAll(userRoles);
                local = this.userRepository.save(user);
            }
        }
        return local;
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findUserByUserName(userName);
    }

    @Override
    public void deleteUserByUserId(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUserByUserName(String userName, User user) throws Exception {
        User updatedUser = getUserByUserName(userName);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAbout(user.getAbout());

        User userWithGivenEmail = this.userRepository.findUserByEmail(user.getEmail());

        // if a user with the provided email already exists and is a different user
        if(userWithGivenEmail != null && userWithGivenEmail.getId() != updatedUser.getId()){
            System.out.println("Email already in use !!");
            throw new Exception("Email already connected with a different user");
        }
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setProfileImage(user.getProfileImage());

        return this.userRepository.save(updatedUser);
    }

    @Override
    public User updatePasswordByUserName(String userName, String password) throws Exception {
        User updatedUser = getUserByUserName(userName);

        if (updatedUser.getPassword().equals(password)) {
            System.out.println("Password same as current password !");
            throw new Exception("New password is same as current password !");
        }
        updatedUser.setPassword(password);
        return this.userRepository.save(updatedUser);
    }

    @Override
    public User deactivateUserByUserName(String userName) {
        User updatedUser = getUserByUserName(userName);
        updatedUser.setEnabled(false);
        return this.userRepository.save(updatedUser);
    }

    @Override
    public User activateUserByUserName(String userName) {
        User updatedUser = getUserByUserName(userName);
        updatedUser.setEnabled(true);
        return this.userRepository.save(updatedUser);
    }

    @Override
    public void deleteUserByUserName(String userName) {
        User user = this.userRepository.findUserByUserName(userName);
        this.deleteUserByUserId(user.getId());
    }


}
