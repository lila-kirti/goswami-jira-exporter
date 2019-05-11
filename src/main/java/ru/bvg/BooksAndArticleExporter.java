package ru.bvg;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.bvg.config.ApplicationConfig;
import ru.bvg.service.ExporterService;

public class BooksAndArticleExporter {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ExporterService service = (ExporterService) context.getBean("exporterService");
        service.exportBooksAndArticles();
    }
}
