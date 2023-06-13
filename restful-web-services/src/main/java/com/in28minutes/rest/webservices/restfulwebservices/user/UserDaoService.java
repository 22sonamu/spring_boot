package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Mara", LocalDate.now().minusYears(24)));
        users.add(new User(2, "Zaza", LocalDate.now().minusYears(25)));
        users.add(new User(3, "Wow", LocalDate.now().minusYears(29)));
    }

    public List<User> findAll(){
        return users;
    }
}
