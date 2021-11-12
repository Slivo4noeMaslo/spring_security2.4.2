package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
