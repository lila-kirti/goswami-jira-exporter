package ru.bvg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bvg.mapper.MediaMapper;
import ru.bvg.model.JiraIssue;
import ru.bvg.model.JiraIssueResponse;
import ru.bvg.model.Media;

@Service
public class ExporterService {
    private static final int MAX_SIZE = 50;

    @Autowired
    private JiraService jiraService;

    @Autowired
    private ExporterDao dao;

    @Autowired
    private ExporterService self;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void export() {
        JiraIssueResponse response = jiraService.getIssues(0, 1);
        int offset = 0;
        for (int i = 0; i < response.getTotal(); i = i + MAX_SIZE) {
            offset += MAX_SIZE;
            response = jiraService.getIssues(offset, MAX_SIZE);
            self.export(response);
        }
    }

    @Transactional
    private void export(JiraIssueResponse response) {
        MediaMapper mediaMapper = new MediaMapper();
        for (JiraIssue jiraIssue : response.getIssues()) {
            Media media = mediaMapper.map(jiraIssue);
            if (jiraIssue.getFields().getPlace() != null) {
                Integer placeId = dao.savePlace(jiraIssue.getFields().getPlace());
                media.setPlaceId(placeId);
            }
        }
    }
}
