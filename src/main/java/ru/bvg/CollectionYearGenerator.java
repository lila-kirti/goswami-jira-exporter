package ru.bvg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CollectionYearGenerator {

    public static void main(String[] args) {
        Path path = Paths.get("G:/javaProjects/collection_year.sql");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(generateCollectionYearMain());
            writer.newLine();

            for (int i = 2019; i > 1990; i--) {
                writer.write(generateCollectionYear(i));
                writer.newLine();
            }
            writer.newLine();

            int j = 50;
            for (int i = 2019; i > 1990; i--) {
                writer.write(generateCollectionYearHierarchy(i, j++));
                writer.newLine();
            }
            writer.newLine();

            for (int i = 2019; i > 1990; i--) {
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
        return "INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('По годам', 'По годам', 'collection', 'year.jpg');";
    }

    private static String generateCollectionYear(int year) {
        return String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('%d', '%d год', 'collection', '%d.jpg');", year, year, year);
    }

    private static String generateCollectionYearHierarchy(int year, int order) {
        return String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По годам'), (select id from collection where full_name='%d год'), %d);", year, order);
    }

    private static List<String>  generateCollectionFolderHierarchy(int year) {
        List<String> list = new ArrayList<>();
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Все лекции'), 1);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Семинары'), 2);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Паломничества'), 3);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Обращения, встречи с учениками'), 4);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Публичные лекции'), 5);", year, year));
        list.add(String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='%d год'), (select id from collection where full_name='%d. Лекции на церемонии посвящения'), 6);", year, year));
        return list;
    }

    private static List<String> generateCollectionFolders(int year) {
        List<String> folders = new ArrayList<>();
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, order_by, direction)  VALUES ('Все', '%d. Все лекции', 'filter', 'all.jpg', '%d-01-01', '%d-12-31', 'date', 'DESC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', '%d. Семинары', 'collection', 'seminar.jpg');", year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Парикрамы', '%d. Паломничества', 'collection', 'parikram.jpg');", year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, order_by, direction)  VALUES ('Обращения, встречи с учениками', '%d. Обращения, встречи с учениками', 'collection', 'treatment.jpg', '%d-01-01', '%d-12-31', 'date', 'DESC');", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id)  VALUES ('Публичные лекции', '%d. Публичные лекции', 'collection', 'public.jpg', '%d-01-01', '%d-12-31', 4);", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id)  VALUES ('Праздники', '%d. Лекции на праздниках', 'collection', 'celebration.jpg', '%d-01-01', '%d-12-31', 2);", year, year, year));
        folders.add(String.format("INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to, category_id)  VALUES ('Инициация', '%d. Лекции на церемонии посвящения', 'collection', 'initiation.jpg', '%d-01-01', '%d-12-31', 7);", year, year, year));
        return folders;
    }
}
