package com.Senorial.Java.Web;

import com.Senorial.Java.Domain.Address;
import com.Senorial.Java.Domain.User;
import com.Senorial.Java.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String getLoginUser (ModelMap model) {

        model.put("user", new User());

        return "login";
    }

    @PostMapping("/login")
    public String postLoginUser(User user) {
        Optional<List<User>> tempUser = userService.findByUsername(user.getUsername());

        if (tempUser.isPresent() && !tempUser.get().isEmpty()) {
            User foundUser = tempUser.get().get(0);

            if (foundUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                return "redirect:/index";
            }
        }

        // Handle the case where username is not found or password doesn't match
        System.out.println("Invalid username or password: " + user.getUsername());
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String getCreateUser (ModelMap model) {

        model.put("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser (User user) {
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/userslist";
    }


    @GetMapping("/index")
    public String getIndexPage (ModelMap model) {

        model.put("user", new User());

        return "index";
    }
    @GetMapping("/users")
    public String getAllUsers (ModelMap model) {
        Set<User> users = userService.findAll();

        model.put("users", users);
        if (users.size() == 1) {
            model.put("user", users.iterator().next());
        }

        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser (ModelMap model, @PathVariable Long userId) {
        User user = userService.findById(userId);

        model.put("user", user);

        return "updateuser";
    }

//    return "redirect:/users/"+user.getUserId();

    @PostMapping("/users/{userId}")
    public String postOneUser (User user) {
        User tempUser = userService.findById(user.getUserId());


        if(user.getPassword().isEmpty() && user.getPassword().isBlank()) {
            user.setPassword(tempUser.getPassword());
        }

        if(null == user.getAddress() ) {
            user.setAddress(tempUser.getAddress());
        }

        if(null == user.getAccounts()) {
            user.setAccounts(tempUser.getAccounts());
        }


        User savedUser = userService.saveUser(user);
        return "redirect:/userslist";
    }


//    @PostMapping("/users/{userId}")
//    public String postOneUser (User user) {
//        userService.saveUser(user);
//        return "redirect:/userslist";
//    }

    @GetMapping("/users/{userId}/update")
    public String getUpdatePage (ModelMap model, @PathVariable Long userId) {
        User user = userService.findById(userId);

        model.put("user", user);

        return "updateaddress";
    }

    @PostMapping("/users/{userId}/update")
    public String postUpdateUser (@ModelAttribute User user,  @PathVariable Long userId) {
        if(null != user.getUserId()){
            userService.saveUser(user);
        }

        return "redirect:/userslist";
    }

    @PostMapping("/users/{userId}/deleteaddress")
    public String postDeleteAddress (@ModelAttribute User user, @PathVariable Long userId) {

        userService.deleteAddress(user);
        return "redirect:/userslist";
    }

    @PostMapping("/users/{userId}/delete")
    public String deleteOneUser (@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/userslist";
    }

    @GetMapping("login3")
    public String registrationPage(@PathVariable User user){
        userService.findAll();
        return "index";
    }

    @GetMapping("userslist")
    public String userListPage(ModelMap model){
        User tempUser = new User("Elon@email.com", "space-x", "Elon Musk");
        userService.saveUser(tempUser);

        User tempUser2 = new User("coffee@email.com", "JavaBeans41", "Hot Coffee");
        userService.saveUser(tempUser2);

        User tempUser3 = new User("record@email.com", "KROQ106.7LA", "Microphone Rick");
        userService.saveUser(tempUser3);

        Set<User> users = userService.findAll();

        model.put("users", users);

        return "userlist";
    }



    @GetMapping("updateuser")
    public String updateuser(User user){
        System.out.println(user);
        return "updateuser";
    }



    @GetMapping("/userinfo/{userId}")
    public String getOneUserInfo (ModelMap model, @PathVariable Long userId) {
        User user = userService.findById(userId);

        model.put("user", user);
        return "userinfo";
    }





}
