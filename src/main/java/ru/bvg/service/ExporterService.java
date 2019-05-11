package ru.bvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.bvg.mapper.MediaMapper;
import ru.bvg.model.*;

import java.util.List;

@Service
public class ExporterService {
    private static final int MAX_SIZE = 50;

    @Autowired
    private JiraService jiraService;

    @Autowired
    private GoswamiRuService goswamiRuService;

    @Autowired
    private ExporterDao exporterDao;

    @Autowired
    private ExporterService self;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void exportFromJira() {
        JiraIssueResponse response = jiraService.getIssues(0, 50);
        int offset = 0;
        for (int i = 0; i < response.getTotal(); i = i + MAX_SIZE) {
            offset += MAX_SIZE;
            response = jiraService.getIssues(offset, MAX_SIZE);
            self.exportFromJira(response);
        }
    }

    @Transactional
    private void exportFromJira(JiraIssueResponse response) {
        MediaMapper mediaMapper = new MediaMapper();
        for (JiraIssue jiraIssue : response.getIssues()) {
            Media media = mediaMapper.map(jiraIssue);
            //место
            if (jiraIssue.getFields().getPlace() != null) {
                Integer placeId = exporterDao.savePlace(jiraIssue.getFields().getPlace());
                media.setPlaceId(placeId);
            }
            //метки
            if (!CollectionUtils.isEmpty(jiraIssue.getFields().getLabels())){
                List<Integer> labels = exporterDao.saveLabels(jiraIssue.getFields().getLabels());
                media.setTags(labels);
            }
            exporterDao.saveMedia(media);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void exportBooksAndArticles() {
        MediaMapper mediaMapper = new MediaMapper();
        GooswamiRuMediaResponse books = goswamiRuService.getBooks(1, 100);
        for (BookArticle book : books.getCollection()) {
            exporterDao.saveMedia(mediaMapper.mapBook(book));
        }
        GooswamiRuMediaResponse articles = goswamiRuService.getArticles(1, 100);
        for (BookArticle article : articles.getCollection()) {
            Media media = mediaMapper.mapArticle(article);
            media.setImgUri("media/article.jpg");
            exporterDao.saveMedia(media);
        }
    }
}
