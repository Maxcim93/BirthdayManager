package com.maxim.webjs.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Максим on 13.09.2016.
 */
public class AppSpringContext {
    private static final ApplicationContext INSTANCE =
            new AnnotationConfigApplicationContext(AppSpringConfig.class);

    public static ApplicationContext getInstance(){
        return INSTANCE;
    }
}
