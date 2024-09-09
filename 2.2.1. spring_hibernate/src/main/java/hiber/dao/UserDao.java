package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void addUser(User user);
   List<User> getUserList();

   List<User> findUserCars(String model, int series);
}
