package com.in28minutes.springboot.learnjpaandhibernate.course.jdbc;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private  CourseJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Lean Java", "in28minutes"));
        repository.insert(new Course(2, "Lean Python", "in28minutes"));
        repository.deleteById(1);

        System.out.println(repository.findById(2));
    }
}
