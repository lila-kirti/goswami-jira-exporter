package ru.bvg;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.bvg.config.ApplicationConfig;
import ru.bvg.service.UserService;

public class UserMigrator {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService service = (UserService) context.getBean("userService");
        service.importUsers();
    }

}
