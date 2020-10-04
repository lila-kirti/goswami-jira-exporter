package ru.bvg.service;

import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.bvg.mapper.MediaMapper;
import ru.bvg.model.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ImporterService {
    private static final int MAX_SIZE = 50;

    @Autowired
    private JiraService jiraService;

    @Autowired
    private GoswamiRuService goswamiRuService;

    @Autowired
    private ImporterDao importerDao;

    @Autowired
    private ImporterService self;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void importFromJira() {
        JiraIssueResponse response = jiraService.getIssues(0, 50);
        int offset = 0;
        for (int i = 0; i < response.getTotal(); i = i + MAX_SIZE) {
            offset += MAX_SIZE;
            response = jiraService.getIssues(offset, MAX_SIZE);
            self.importFromJira(response);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void importDailyShloka() {
        JiraIssueResponse response = jiraService.getDailyShlokaIssues(0, 50);
        int offset = 0;
        Path path = Paths.get("E:/javaProjects/daily_shloka.sql");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 0; i < response.getTotal(); i = i + MAX_SIZE) {
                writeDailyShloka(writer, response);
                offset += MAX_SIZE;
                response = jiraService.getDailyShlokaIssues(offset, MAX_SIZE);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    private void importFromJira(JiraIssueResponse response) {
        MediaMapper mediaMapper = new MediaMapper();
        for (JiraIssue jiraIssue : response.getIssues()) {
            Media media = mediaMapper.map(jiraIssue);

            if (media.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == 1956)
                continue;
            //место
            if (jiraIssue.getFields().getPlace() != null) {
                Integer placeId = importerDao.savePlace(jiraIssue.getFields().getPlace());
                media.setPlaceId(placeId);
            }
            //метки
            if (!CollectionUtils.isEmpty(jiraIssue.getFields().getLabels())) {
                List<Integer> labels = importerDao.saveLabels(jiraIssue.getFields().getLabels());
                media.setTags(labels);
            }
            importerDao.saveMedia(media);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void importBooksAndArticles() {
        MediaMapper mediaMapper = new MediaMapper();
        GooswamiRuMediaResponse books = goswamiRuService.getBooks(1, 100);
        for (BookArticle book : books.getCollection()) {
            importerDao.saveMedia(mediaMapper.mapBook(book));
        }
//        GooswamiRuMediaResponse articles = goswamiRuService.getArticles(1, 100);
//        for (BookArticle article : articles.getCollection()) {
//            Media media = mediaMapper.mapArticle(article);
//            media.setImgUri("article/article-default.jpg");
//            importerDao.saveMedia(media);
//        }
    }

    public void writeDailyShloka(BufferedWriter writer, JiraIssueResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (JiraIssue jiraIssue : response.getIssues()) {
                JiraField fields = jiraIssue.getFields();
                String transcript = getTranscript(fields.getText());
                writer.write(String.format("INSERT INTO everyday_verse (title, comment_url, published_at, catalogue_id, comment_text)  VALUES ('%s', '%s', '%s', null, %s);",
                        fields.getPrettyName(),
                        getFileUrl(fields.getLectureDate(), fields.getFileName()),
                        sdf.format(fields.getLectureDate()),
                        !StringUtils.isEmpty(transcript) ? wrap(transcript) : "null"));
                writer.newLine();
                String wordByWord = getWordByWord(fields.getText());
                String translation = getTranslation(fields.getText());
                writer.write(String.format("INSERT INTO verse (title, file_url, sanskrit, word_by_word, translation, cover)  VALUES ('%s', '%s', %s, %s, %s, null);",
                        fields.getPrettyName(),
                        getFileUrl(fields.getLectureDate(), fields.getFileName()),
                        !StringUtils.isEmpty(fields.getDescription()) ? wrap(removeTags(fields.getDescription())) : "null",
                        !StringUtils.isEmpty(wordByWord) ? wrap(wordByWord) : "null",
                        !StringUtils.isEmpty(translation) ? wrap(translation) : "null")
                );
                writer.newLine();
                writer.write(String.format("INSERT INTO everyday_verse_verse (everyday_verse_id, verse_id)  VALUES ((select id from everyday_verse where title='%s'), (select id from verse where title='%s'));",
                        fields.getPrettyName(), fields.getPrettyName()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileUrl(Date date, String name) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        StrBuilder url = new StrBuilder("http://bvgm.org/media/audio/");

        url.append(c.get(Calendar.YEAR)).append("/");
        int month = c.get(Calendar.MONTH) + 1;
        if (month < 10) {
            url.append("0").append(String.valueOf(month));
        } else {
            url.append(String.valueOf(month));
        }
        url.append("/").append(name);
        return url.toString();
    }

    private String getWordByWord(String text) {
        if (text == null || !text.contains("$"))
            return null;
        String[] parts = text.split("\\$");
        return removeTags(parts[0]);
    }

    private String getTranslation(String text) {
        if (text == null || !text.contains("$"))
            return null;
        String[] parts = text.split("\\$");
        return removeTags(parts[1]);
    }

    private String getTranscript(String text) {
        if (text == null)
            return null;
        String[] parts = text.split("\\$");
        String transcript = (parts.length == 3) ? parts[2] : parts[0];
        return transcript
                .replaceAll("<p>", "")
                .replaceAll("</p>", "\n")
                .replaceAll("<br />", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("<br/>", "\n")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&laquo;", "«")
                .replaceAll("&raquo;", "»")
                .replaceAll("&ndash;", "–")
                .replaceAll("&mdash;", "—")
                .replaceAll("&hellip;", "...")
                .replaceAll("'", "''").trim();
    }

    private String removeTags(String text) {
        return text.replaceAll("<em>", "").replaceAll("</em>", "")
                .replaceAll("<br />", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("<br/>", "\n")
                .replaceAll("<p>", "")
                .replaceAll("</p>", "")
                .replaceAll("\\$", "")
                .replaceAll("<b>", "")
                .replaceAll("</b>", "")
                .replaceAll("<strong>", "")
                .replaceAll("</strong>", "")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&laquo;", "«")
                .replaceAll("&raquo;", "»")
                .replaceAll("&ndash;", "–")
                .replaceAll("&mdash;", "—")
                .replaceAll("&hellip;", "...")
                .replaceAll("'", "''").trim();
    }

    private String wrap(String text){
        return "'" + text + "'";
    }
}
