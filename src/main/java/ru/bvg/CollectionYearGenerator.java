package ru.bvg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Генератор sql скриптов для подборки "По годам"
 */
public class CollectionYearGenerator {

    public static void main(String[] args) {
        Path path = Paths.get("G:/javaProjects/collection_year.sql");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(generateCollectionYearMain());
            writer.newLine();

            for (int i = 2020; i > 1991; i--) {
                writer.write(generateCollectionYear(i));
                writer.newLine();
            }
            writer.newLine();

            int j = 50;
            for (int i = 2020; i > 1991; i--) {
                writer.write(generateCollectionYearHierarchy(i, j++));
                writer.newLine();
            }
            writer.newLine();

            for (int i = 2020; i > 1991; i--) {
                for (String folder : generateCollectionFolders(i)) {
                    writer.write(folder);
                    writer.newLine();
                }
                for (String folder : generateCollectionFolderHierarchy(i)) {
                    writer.write(folder);
                    writer.newLine();
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateCollectionYearMain() {
        return "INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('По годам', 'По годам', 'collection', 'collection/year.jpg');";
    }

    private static String generateCollectionYear(int year) {
        return String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('%d', '%d год', 'collection', 'collection/%d.jpg');", year, year, year);
    }

    private static String generateCollectionYearHierarchy(int year, int order) {
        return String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По годам'), (select id from collection where full_name='%d год'), %d);", year, order);
    }

    private static List<String>  generateCollectionFolderHierarchy(int year) {
        List<String> list = new ArrayList<>();
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Все лекции'), 1);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Семинары'), 2);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Паломничества'), 3);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Встречи с учениками'), 4);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Публичные лекции'), 5);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Лекции на праздниках'), 6);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Лекции на церемонии посвящения'), 7);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Ретриты и фестивали'), 8);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Обращения'), 9);", year, year));
        return list;
    }

    private static List<String> generateCollectionFolders(int year) {
        List<String> folders = new ArrayList<>();
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, order_by, direction)  VALUES ('Все', '%d. Все лекции', 'filter', 'collection/all.jpg', '%d-01-01', '%d-12-31', 'date', 'ASC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', '%d. Семинары', 'collection', 'collection/seminar.jpg');", year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id, order_by, direction)  VALUES ('Паломничества', '%d. Паломничества', 'filter', 'collection/parikram.jpg', '%d-01-01', '%d-12-31', 1, 'date', 'ASC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id, order_by, direction)  VALUES ('Встречи с учениками', '%d. Обращения, встречи с учениками', 'filter', 'collection/treatment.jpg', '%d-01-01', '%d-12-31', 5, 'date', 'ASC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id, order_by, direction)  VALUES ('Публичные лекции', '%d. Публичные лекции', 'filter', 'collection/public.jpg', '%d-01-01', '%d-12-31', 4, 'date', 'ASC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id, order_by, direction)  VALUES ('Праздники', '%d. Лекции на праздниках', 'filter', 'collection/celebration.jpg', '%d-01-01', '%d-12-31', 2, 'date', 'ASC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id, order_by, direction)  VALUES ('Инициация', '%d. Лекции на церемонии посвящения', 'filter', 'collection/initiation.jpg', '%d-01-01', '%d-12-31', 7, 'date', 'ASC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты и фестивали', '%d. Ретриты и фестивали', 'collection', 'collection/9ef81568-b905-4c02-ba24-bb2e05e35329.jpg');", year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id, order_by, direction)  VALUES ('Обращения', '%d. Обращения', 'filter', 'collection/treatment.jpg', '%d-01-01', '%d-12-31', 3, 'date', 'ASC');", year, year, year));
        return folders;
    }
}
