package ru.bvg;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.bvg.config.ApplicationConfig;
import ru.bvg.service.ImporterService;

public class GoswamiJiraExporter {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ImporterService service = (ImporterService) context.getBean("exporterService");
        service.importFromJira();
    }
}
