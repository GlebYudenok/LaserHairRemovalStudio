package by.gyudenok.dao;

import by.gyudenok.domain.User;

public interface UserDao extends Dao<User> {
    void registration();
    void signIn();
}
