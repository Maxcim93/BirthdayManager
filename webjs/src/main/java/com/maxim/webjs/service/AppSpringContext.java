package com.maxim.webjs.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Максим on 13.09.2016.
 */
public class AppSpringContext {
    private static final ApplicationContext INSTANCE =
            new ClassPathXmlApplicationContext("spring_storages.xml");

    public static ApplicationContext getInstance(){
        return INSTANCE;
    }
}
