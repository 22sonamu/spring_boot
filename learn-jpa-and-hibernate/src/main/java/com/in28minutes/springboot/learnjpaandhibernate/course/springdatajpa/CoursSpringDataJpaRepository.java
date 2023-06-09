package com.in28minutes.springboot.learnjpaandhibernate.course.springdatajpa;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursSpringDataJpaRepository extends JpaRepository<Course, Long> {

    //custom method 정의가능

    List<Course> findByAuthor(String author);

    List<Course> findByName(String name);
}
