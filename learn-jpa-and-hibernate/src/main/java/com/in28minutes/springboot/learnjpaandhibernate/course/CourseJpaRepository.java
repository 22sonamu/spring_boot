package com.in28minutes.springboot.learnjpaandhibernate.course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    @Autowired
    private EntityManager entityManager;

    public void insert(Course course){
        entityManager.merge(course);
    }

    public void deleteById(long id){
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    public Course findById(long id){
        Course course = entityManager.find(Course.class, id);
        return course;
    }

}
