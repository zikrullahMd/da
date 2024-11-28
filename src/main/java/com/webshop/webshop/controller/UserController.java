package com.webshop.webshop.controller;
import com.webshop.webshop.model.UserModel;
import com.webshop.webshop.utility.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

import static com.webshop.webshop.ApplicationConstants.users;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all-users")
    public ArrayList<UserModel> getAllUsers() {
        return users;
    }

    @GetMapping("/get-user/{userid}")
    public UserModel getUserById(@PathVariable("userid") int userid) {
        return userService.getUserById(userid);
    }

    @PostMapping("/add-user")
    public UserModel addUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String phone) {
        int userId = new Random().nextInt(1000);
        users.add(new UserModel(userId, username, password, email, phone));
        return users.get(users.size()-1);
    }
}
