package web.controller;

import hiber.config.HiberConfig;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);

		UserService userService = context.getBean(UserService.class);

		List<User> userList = userService.listUsers();

		model.addAttribute("users", userList);
		return "index";
	}

	@PostMapping(value = "/add")
	public String addUser2(@RequestParam String name, @RequestParam String lastName,
						   @RequestParam String email, ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);

		UserService userService = context.getBean(UserService.class);
		userService.add(new User(name, lastName, email));

		List<User> userList = userService.listUsers();

		model.addAttribute("users", userList);
		return "index";
	}

	@PostMapping(value = "/update")
	public String updateUser(@ModelAttribute(value="user") User user, ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);
		System.out.println(user);
		UserService userService = context.getBean(UserService.class);
		userService.update(user);
		List<User> userList = userService.listUsers();

		model.addAttribute("users", userList);
		return "index";
	}

	@PostMapping(value = "/delete")
	public String deleteUser(@ModelAttribute(value="user") User user, ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);
		System.out.println(user);
		UserService userService = context.getBean(UserService.class);
		userService.delete(user);
		List<User> userList = userService.listUsers();

		model.addAttribute("users", userList);
		return "index";
	}
	
}