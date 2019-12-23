package ru.bvg;

import ru.bvg.enumeration.CollectionTypeEnum;
import ru.bvg.model.Collection;
import ru.bvg.util.CollectionExcelParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Генератор sql скриптов семинаров на основе xml файла
 */
public class CollectionsGenerator {
    private static Map<String, Integer> orderMap;

    static {
        orderMap = new HashMap<>();
        orderMap.put(CollectionTypeEnum.BG_SEMINAR.getText(), 500);
        orderMap.put(CollectionTypeEnum.SB_SEMINAR.getText(), 500);
        orderMap.put(CollectionTypeEnum.CC_SEMINAR.getText(), 500);
        orderMap.put(CollectionTypeEnum.SEMINAR.getText(), 500);
        orderMap.put(CollectionTypeEnum.PARIKRAM.getText(), 500);
        orderMap.put(CollectionTypeEnum.DISCIPLE_RETREAT.getText(), 500);
        orderMap.put(CollectionTypeEnum.SADHU_SANGA_RETREAT.getText(), 500);
        orderMap.put(CollectionTypeEnum.BHAKTI_SANGAM_RETREAT.getText(), 500);
        orderMap.put(CollectionTypeEnum.HOLY_NAME_RETREAT.getText(), 500);
        orderMap.put(CollectionTypeEnum.MASTER_RETREAT.getText(), 500);
        orderMap.put(CollectionTypeEnum.PRITI_LAKSHANAM.getText(), 500);
        orderMap.put(CollectionTypeEnum.SHDM_RETREAT.getText(), 500);
        for (int i = 1991; i < 2020; i++) {
            orderMap.put(i + ". " + CollectionTypeEnum.SEMINAR.getText(), 100);
            orderMap.put(i + ". " + CollectionTypeEnum.PARIKRAM.getText(), 100);
            orderMap.put(i + ". " + CollectionTypeEnum.RETREATS.getText(), 100);
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("G:/javaProjects/collections.sql");
        CollectionExcelParser excelParser = new CollectionExcelParser();
        Map<Integer, List<Collection>> map = excelParser.parseByYear();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Integer year : map.keySet()) {
                List<Collection> collections = map.get(year);
                for (Collection collection : collections) {
                    writeCollection(writer, collection);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCollection(BufferedWriter writer, Collection collection) throws IOException {
        String title = collection.getTitle().replaceAll("\\'", "''");
        writer.write(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('%s', '%s', 'lecture', 'collection/%s.jpg');",
                title, title, collection.getImage()));
        writer.newLine();
        for (String media : collection.getIssues()) {
            writer.write(String.format("INSERT INTO collection_media (collection_id, media_id, ordern)  VALUES ((select id from collection where full_name='%s'), (select id from media where jira_ref='%s'), %d);",
                    title, media, collection.getIssues().indexOf(media) + 1));
            writer.newLine();
        }
        writer.newLine();
        for (String type : collection.getTypes()) {
            if (orderMap.get(type) == null) {
                throw new IllegalStateException("Unknown collection type: " + type);
            }
            writer.write(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%s'), (select id from collection where full_name='%s'), %d);", type, title, orderMap.get(type)));
            writer.newLine();
            orderMap.put(type, orderMap.get(type) + 1);
        }
        writer.newLine();
    }
}
