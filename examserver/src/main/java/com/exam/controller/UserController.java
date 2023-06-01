package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        Role role = this.roleRepository.findByRole("USER");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        return this.userService.createUser(user, userRoles);
    }

    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName){
        return this.userService.getUserByUserName(userName);
    }

    @DeleteMapping("/{userName}")
    public void deleteUserByUserName(@PathVariable("userName") String userName){
        this.userService.deleteUserByUserName(userName);

    }

//    @DeleteMapping("/{userId}")
//    public void deleteUserByUserId(@PathVariable("userId") Long userId){
//        this.userService.deleteUserByUserId(userId);
//    }

    @PutMapping("/{userName}")
    public User updateUserByUserName(@PathVariable("userName") String userName,
                                     @RequestBody User user) throws Exception {
        return this.userService.updateUserByUserName(userName, user);
    }

    // TODO update password

    // TODO deactivate user

    
}
