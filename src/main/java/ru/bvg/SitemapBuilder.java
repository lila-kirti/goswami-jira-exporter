package ru.bvg;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.bvg.config.ApplicationConfig;
import ru.bvg.model.Media;
import ru.bvg.service.ImporterDao;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SitemapBuilder {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ImporterDao service = (ImporterDao) context.getBean("importerDao");
        List<Media> list = service.getMediaIdWithType();
        try (FileOutputStream outputStream = new FileOutputStream(new File("sitemap_media.xml"))) {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            output.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
            XMLStreamWriter writer = null;
            try {
                writer = new IndentingXMLStreamWriter(output.createXMLStreamWriter(outputStream));
                writer.setDefaultNamespace("http://www.sitemaps.org/schemas/sitemap/0.9");
                writer.writeStartDocument("1.0");
                writer.writeStartElement("http://www.sitemaps.org/schemas/sitemap/0.9", "urlset");
                for (Media item : list) {
                    if (!"audio".equals(item.getType()) && !"article".equals(item.getType()))
                        continue;
                    String type = item.getType().equals("audio")? "lecture" : "article";
                    writer.writeStartElement("url");
                    write(writer, "loc", "https://goswami.ru/"+ type + "/" + item.getId().toString());
                    write(writer, "changefreq", "weekly");
                    write(writer, "priority", "1");
                    writer.writeEndElement(); //url
                }
                writer.writeEndElement(); //urlset
                writer.writeEndDocument();
            } catch (XMLStreamException e) {
//                logger.error("Could not generate sitemap", e);
            } finally {
                if (writer != null)
                    try {
                        writer.close();
                    } catch (XMLStreamException e) {
//                        logger.error("Could not generate sitemap", e);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
        writer.writeStartElement(name);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }
}
