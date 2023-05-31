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
    public void deleteUserByUserName(String userName) {
        User user = this.userRepository.findUserByUserName(userName);
        this.deleteUserByUserId(user.getId());
    }


}
