package hiber.dao;

import hiber.model.Role;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   @Autowired
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return entityManager.createQuery("from User").getResultList();
   }

   @Override
   public void delete(User user) {
      User newUser = entityManager.find(User.class, user.getId());
      entityManager.remove(newUser);
   }

   @Override
   public void update(User user) {
      User newUser = entityManager.find(User.class, user.getId());
      user.setRoles(newUser.getRoles());
      entityManager.merge(user);
   }

   @Override
   public User getUserByName(String name) {
      return entityManager.createQuery("from User u WHERE u.username = :value", User.class)
              .setParameter("value", name).getSingleResult();
   }

   @Override
   public UserDetails getUserDetailsByName(String name) {
      UserDetails loadedUser;

      User client = entityManager.createQuery("from User u WHERE u.username = :value", User.class)
              .setParameter("value", name).getSingleResult();

      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role: client.getRoles()) {
         grantedAuthorities.add(role);
      }

      loadedUser = new org.springframework.security.core.userdetails.User(
              client.getUsername(), client.getPassword(), grantedAuthorities);

      return loadedUser;
   }

}
