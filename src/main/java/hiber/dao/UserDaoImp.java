package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.getTransaction().begin();
      entityManager.persist(user);
      entityManager.getTransaction().commit();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return entityManager.createQuery("from User").getResultList();
   }

   @Override
   public void delete(User user) {
      User newUser = entityManager.find(User.class, user.getId());

      entityManager.getTransaction().begin();
      entityManager.remove(newUser);
      entityManager.getTransaction().commit();
   }

   @Override
   public void update(User user) {
      User newUser = entityManager.find(User.class, user.getId());

      entityManager.getTransaction().begin();
      newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
      newUser.setEmail(user.getEmail());
      entityManager.getTransaction().commit();
   }

}
