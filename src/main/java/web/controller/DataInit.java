package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void createUserAndAdmin() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        Set<Role> rolesWithUser = new HashSet<>();
        rolesWithUser.add(userRole);
        Set<Role> rolesWithAdmin = new HashSet<>();
        rolesWithAdmin.add(adminRole);
        rolesWithAdmin.add(userRole);
        User user1 = new User("user", "user",
                "Aleksandr", "Pushkin",
                32, "pushkin@gmail.com", rolesWithUser);
        User user2 = new User("admin", "admin",
                "Sergey", "Esenin",
                28, "hooligan@gmail.com", rolesWithAdmin);
        userService.addUser(user1);
        userService.addUser(user2);
    }
}
