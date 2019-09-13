package ru.bvg;

import org.apache.commons.codec.digest.DigestUtils;
import ru.bvg.model.Collection;
import ru.bvg.util.CollectionExcelParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Генератор sql скриптов семинаров на основе xml файла
 */
public class CollectionsGenerator {
    public static void main(String[] args) {
        Path path = Paths.get("G:/javaProjects/collections.sql");
        CollectionExcelParser excelParser = new CollectionExcelParser();
        Map<Integer, List<Collection>> map = excelParser.parseByYear();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Integer year : map.keySet()) {
                List<Collection> collections = map.get(year);
                for (Collection collection : collections) {
                    writeCollection(writer, collection, year, collections.indexOf(collection) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCollection(BufferedWriter writer, Collection collection, int year, int index) throws IOException {
        String title = collection.getTitle().replaceAll("\\'", "''");
        writer.write(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('%s', '%s', 'lecture', 'collection/%s.jpg');",
                title, title, DigestUtils.sha1Hex(title)));
        writer.newLine();
        writer.write(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d. Семинары'), (select id from collection where full_name='%s'), %d);", year, title, index));
        writer.newLine();
        for (String media : collection.getIssues()) {
            writer.write(String.format("INSERT INTO collection_media (collection_id, media_id, ordern)  VALUES ((select id from collection where full_name='%s'), (select id from media where jira_ref='%s'), %d);",
                    title, media, collection.getIssues().indexOf(media) + 1));
            writer.newLine();
        }
        writer.newLine();
    }
}
