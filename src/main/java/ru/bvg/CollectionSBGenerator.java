package ru.bvg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Генератор sql скриптов для подборки "По священным писания" -> "Шримад Бхагаватам"
 */
public class CollectionSBGenerator {

    public static void main(String[] args) {
        Path path = Paths.get("G:/javaProjects/sb.sql");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(generateCollectionSeminar());
            writer.newLine();

            for (int i = 1; i <= 12; i++) {
                writer.write(generateCollectionCanto(i));
                writer.newLine();
            }
            writer.newLine();

            for (int i = 1; i <= 12; i++) {
                writer.write(generateCantoHierarchy(i));
                writer.newLine();
            }
            writer.newLine();

            writeChapterWithHierarchy(writer, 1, 19);
            writeChapterWithHierarchy(writer, 2, 10);
            writeChapterWithHierarchy(writer, 3, 33);
            writeChapterWithHierarchy(writer, 4, 31);
            writeChapterWithHierarchy(writer, 5, 26);
            writeChapterWithHierarchy(writer, 6, 19);
            writeChapterWithHierarchy(writer, 7, 15);
            writeChapterWithHierarchy(writer, 8, 24);
            writeChapterWithHierarchy(writer, 9, 24);
            writeChapterWithHierarchy(writer, 10, 90);
            writeChapterWithHierarchy(writer, 11, 31);
            writeChapterWithHierarchy(writer, 12, 13);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeChapterWithHierarchy(BufferedWriter writer, int canto, int chapters) throws IOException {
        for (int i = 1; i <= chapters; i++) {
            writer.write(generateCollectionChapter(canto, i));
            writer.newLine();
            writer.write(generateChapterHierarchy(canto, i, i));
            writer.newLine();
        }
        writer.newLine();
    }

    private static String generateCollectionSeminar() {
        return "INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары','Семинары по Шримад-Бхагаватам', 'collection', 'collection/sb-seminar.jpg');";
    }

    private static String generateCollectionCanto(int canto) {
        return String.format("INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь %d', 'Шримад-Бхагаватам. Песнь %d', 'collection', 'collection/sb%d.jpg');", canto, canto, canto);
    }

    private static String generateCollectionChapter(int canto, int chapter) {
        return String.format("INSERT INTO collection (short_name, full_name, source, img_url, canto, chapter, order_by, direction)  VALUES ('Глава %d', 'Шримад-Бхагаватам. Песнь %d. Глава %d', 'filter', 'collection/sb%d_%d.jpg', %d, %d, 'verse', 'ASC');",
                chapter, canto, chapter, canto, chapter, canto, chapter);
    }

    private static String generateChapterHierarchy(int canto, int chapter, int order) {
        return String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Шримад-Бхагаватам. Песнь %d'), (select id from collection where full_name='Шримад-Бхагаватам. Песнь %d. Глава %d'), %d);",
                canto, canto, chapter, order);
    }

    private static String generateCantoHierarchy(int canto) {
        return String.format("INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Шримад-Бхагаватам'), (select id from collection where full_name='Шримад-Бхагаватам. Песнь %d'), %d);",
                canto, canto);
    }
}
