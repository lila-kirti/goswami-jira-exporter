package ru.bvg;

import ru.bvg.model.Collection;
import ru.bvg.util.CollectionExcelParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * Генератор sql скриптов семинаров на основе xml файла
 */
public class CollectionsGenerator {
    public static void main(String[] args) {
        Path path = Paths.get("G:/javaProjects/collections.sql");
        CollectionExcelParser excelParser = new CollectionExcelParser();
        List<Collection> list = excelParser.parse();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Collection collection : list) {
                writeCollection(writer, collection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCollection(BufferedWriter writer, Collection collection) throws IOException {
        writer.write(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('%s', '%s', 'collection', 'collection/%s.jpg');",
                collection.getTitle(), collection.getTitle(), UUID.randomUUID()));
        writer.newLine();
        for (String media : collection.getIssues()) {
            writer.write(String.format("INSERT INTO collection_media (collection_id, media_id, ordern)  VALUES ((select id from collection where full_name='%s'), (select id from media where jira_ref='%s'), %d);",
                    collection.getTitle(), media, collection.getIssues().indexOf(media) + 1));
            writer.newLine();
        }
        writer.newLine();
    }
}
