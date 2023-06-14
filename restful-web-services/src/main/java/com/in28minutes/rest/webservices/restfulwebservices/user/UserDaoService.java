package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;
    static {
        users.add(new User(++userCount, "Mara", LocalDate.now().minusYears(24)));
        users.add(new User(++userCount, "Zaza", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount, "Wow", LocalDate.now().minusYears(29)));
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId() == id;
        //조건에 맞는 데이터가 없으면 null보내기 , 200
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
}
