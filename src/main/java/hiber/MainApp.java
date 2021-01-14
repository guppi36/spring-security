package hiber;

import hiber.config.HiberConfig;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

// Class for fast add some users to data base
public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HiberConfig.class);

        UserService userService = context.getBean(UserService.class);

        List<User> userList = new ArrayList<>();
        userList.add(new User("Vlad", "Ves", "vlad@gmail.com"));
        userList.add(new User("Katya", "Ivan", "yasha@mail.ru"));
        userList.add(new User("Alex", "Grow", "mdma@fsb.ru"));
        userList.add(new User("Gena", "Mix", "fcin@gmail.com"));
        userList.add(new User("Victor", "Shep", "pobeda@yandex.ru"));

        for (User user : userList) {
            userService.add(user);
        }

        userList.clear();

        userList = userService.listUsers();

        for (User user: userList) {
            System.out.println(user.toString());
        }
    }
}
