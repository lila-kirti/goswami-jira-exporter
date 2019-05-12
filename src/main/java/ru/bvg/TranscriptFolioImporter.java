package ru.bvg;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.bvg.config.ApplicationConfig;
import ru.bvg.service.FolioService;

public class TranscriptFolioImporter {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        FolioService service = (FolioService) context.getBean("folioService");
        service.importTranscripts();

    }
}

