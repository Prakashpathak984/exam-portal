package com.exam;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("System started !!");

//		User user = new User();
//		user.setFirstName("PAVAN");
//		user.setLastName("KALYAN");
//		user.setEmail("PAVAN@gmail.com");
//		user.setPhone("812546");
//		user.setAbout("I am a bba student");
//		user.setUserName("PAVAN");
//		user.setPassword("abahdgadc");
//		user.setProfileImage("this would contain his profile image url");
//
//		Role role1 = this.roleRepository.findByRole("STAFF");
//
//		if(role1 == null){
//			role1 = new Role();
//			role1.setRole("STAFF");
//			System.out.println("this time it should run");
//		}
//
////		role1.setRoleId(44L);
//
////		Role role1 = new Role();
////		role1.setRole("ADMIN");
////		Role role2 = new Role(64L, "User");
//
//		UserRole userRole1 = new UserRole();
//		userRole1.setRole(role1);
//		userRole1.setUser(user);
//
////		UserRole userRole2 = new UserRole();
////		userRole2.setRole(role2);
////		userRole2.setUser(user);
//
//		Set<UserRole> userRoles = new HashSet<>();
//		userRoles.add(userRole1);
////		userRoles.add(userRole2);
//
//		User local = this.userService.createUser(user, userRoles);
//		System.out.println(local.getUserName());

	}
}
