package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
