package com.Senorial.Java.Bootstrap;

import com.Senorial.Java.Domain.User;
import com.Senorial.Java.Repositories.UserRepository;
import com.Senorial.Java.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootstrapData implements CommandLineRunner{
    ///////////////////// ///////////////////// ///////////////////// /////////////////////
    private final UserRepository userRepository;

    private final UserService userService;

    public BootstrapData(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


///////////////////// ///////////////////// ///////////////////// /////////////////////



    @Override
    public void run(String... args) throws Exception {
        loadUsersIntoDataBase();

    }



    ///////////////////// ///////////////////// ///////////////////// /////////////////////
    private void loadUsersIntoDataBase (){
        if (userRepository.count() == 0) {

            User tempUser = new User("kors@email.com", "password", "Micahael Kors");
            userService.saveUser(tempUser);

            User tempUser2 = new User("haus@email.com", "pass123", "Haus Musik");
            userService.saveUser(tempUser2);

            User tempUser3 = new User("swingline@email.com", "password", "Swingline Stapler");
            userService.saveUser(tempUser3);







        }



    }
}
