package web.controller;

import hiber.config.HiberConfig;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printStart(ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);

		UserService userService = context.getBean(UserService.class);

		List<User> userList = userService.listUsers();

		model.addAttribute("users", userList);
		return "index";
	}

	@PostMapping(value = "/add")
	public String addUser2(@RequestParam String name, @RequestParam String lastName,
						   @RequestParam String email,
						   @RequestParam String password,ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);

		UserService userService = context.getBean(UserService.class);
		userService.add(new User(name, lastName, email, password));

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

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
}