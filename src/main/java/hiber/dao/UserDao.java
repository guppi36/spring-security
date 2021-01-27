package hiber.dao;

import hiber.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void delete(User user);
   void update(User user);
   User getUserByName(String name);
   UserDetails getUserDetailsByName(String name);
}
