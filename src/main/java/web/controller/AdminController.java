package web.controller;

import hiber.config.HiberConfig;
import hiber.model.Role;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

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
	public String addUser2(@RequestParam String username,
						   @RequestParam String email,
						   @RequestParam String password,
						   @RequestParam(defaultValue = "false") boolean isAdmin, ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);

		User newUser = new User(username, email, password);
		Set<Role> roles = new HashSet<>();
		roles.add(Role.getUserRole());
		if(isAdmin) roles.add(Role.getAdminRole());

		newUser.setRoles(roles);

		UserService userService = context.getBean(UserService.class);
		userService.add(newUser);

		List<User> userList = userService.listUsers();
		model.addAttribute("users", userList);
		return "index";
	}

	@PostMapping(value = "/update")
	public String updateUser(@ModelAttribute(value="user") User user, ModelMap model) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(HiberConfig.class);

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
