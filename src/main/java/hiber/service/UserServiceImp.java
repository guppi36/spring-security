package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public void delete(User user) { userDao.delete(user); }

   @Transactional
   @Override
   public void update(User user) {
      User newUser = userDao.getUserByName(user.getUsername());
      user.setRoles(newUser.getRoles());
      userDao.update(user);
   }

   @Transactional
   @Override
   public User getUserByName(String username) {
      return userDao.getUserByName(username);
   }
}
