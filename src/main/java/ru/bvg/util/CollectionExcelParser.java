package ru.bvg.util;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import ru.bvg.model.Collection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CollectionExcelParser {
    public List<Collection> parse() {
        List<Collection> collections = new ArrayList<>();
        Workbook workbook;
        try (InputStream is = new FileInputStream(ResourceUtils.getFile("classpath:collections.xlsx"))) {
            workbook = StreamingReader.builder()// buffer size to use when reading InputStream to file (defaults to 1024)
                    .open(is);
            for (Sheet sheet : workbook) {
                if (workbook.getSheetIndex(sheet) > 4)
                    continue;
                parseSheet(sheet, collections);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collections;
    }

    public Map<Integer, List<Collection>> parseByYear() {
        Map<Integer, List<Collection>> map = new LinkedHashMap<>();
        Workbook workbook;
        try (InputStream is = new FileInputStream(ResourceUtils.getFile("classpath:collections.xlsx"))) {
            workbook = StreamingReader.builder()// buffer size to use when reading InputStream to file (defaults to 1024)
                    .bufferSize(100)
                    .open(is);
            for (Sheet sheet : workbook) {
                if (!sheet.getSheetName().trim().equals("2018") && !sheet.getSheetName().trim().equals("2017")) continue;
                List<Collection> collections = new ArrayList<>();
                parseSheet(sheet, collections);
                map.put(Integer.parseInt(sheet.getSheetName().trim()), collections);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private void parseSheet(Sheet sheet, List<Collection> collections) {
        String title = null;
        List<String> issues = new ArrayList<>();
        List<String> types = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getCell(0) != null && !StringUtils.isEmpty(row.getCell(0).getStringCellValue())) {
                if (!issues.isEmpty()) {
                    collections.add(new Collection(title, issues, types));
                    issues = new ArrayList<>();
                }
                title = row.getCell(0).getStringCellValue();
                types = Arrays.asList(row.getCell(2).getStringCellValue().split(","));
            } else if (!StringUtils.isEmpty(row.getCell(1).getStringCellValue())) {
                issues.add(row.getCell(1).getStringCellValue());
            }
        }
        collections.add(new Collection(title, issues, types));
    }
}
