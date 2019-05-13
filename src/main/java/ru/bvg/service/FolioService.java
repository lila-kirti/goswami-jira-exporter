package ru.bvg.service;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.bvg.model.JiraIssue;
import ru.bvg.model.JiraIssueResponse;
import ru.bvg.model.Transcript;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolioService {
    private static final Logger logger = LoggerFactory.getLogger(FolioService.class);
    private static final String[] PREFIX = new String[]{"<p><strong>Аннотация", "<p style=\"html-align: justify;\"><strong>Аннотация"};


    @Value("${folio.rss}")
    private String folioRss;

    @Autowired
    private ImporterDao dao;
    @Autowired
    private JiraService jiraService;

    public void findJiraRef() {
        List<Transcript> list = dao.getTranscripts();
        for (Transcript transcript : list) {
            JiraIssueResponse issues = jiraService.getByDate(transcript.getPublish());
            if (!issues.getIssues().isEmpty()) {
                dao.saveTranscriptJiraRef(transcript.getId(), issues.getIssues().stream().map(JiraIssue::getKey).collect(Collectors.toList()));
            }
        }
    }

    public void importTranscripts() {
        for (int i = 1; i <= 126; i++) {
            try {
                List<Transcript> list = getFeed(i);
                for (Transcript transcript : list) {
                    dao.saveTranscript(transcript);
                }
            } catch (IOException | FeedException e) {
                logger.error("Error on page " + i, e);
            }
        }
    }

    public List<Transcript> getFeed(int page) throws IOException, FeedException {
        List<Transcript> list = new ArrayList<>();
        URL url = new URL("http://folio.goswami.ru/?feed=rss2&paged=" + page);
        XmlReader xmlReader = null;
        try {
            xmlReader = new XmlReader(url);
            SyndFeed feeder = new SyndFeedInput(false).build(xmlReader);
            logger.info("Page " + page);

            for (Iterator iterator = feeder.getEntries().iterator(); iterator.hasNext(); ) {
                SyndEntry syndEntry = (SyndEntry) iterator.next();
                String value = ((SyndContent) syndEntry.getContents().get(0)).getValue();
                if (value.startsWith("<ul>") || value.startsWith("<ol>") || value.contains("<li><a href=\"http://folio.goswami.ru"))
                    continue;

                list.add(new Transcript(syndEntry.getPublishedDate(), ((SyndContent) syndEntry.getContents().get(0)).getValue()));
            }
        } finally {
            if (xmlReader != null)
                xmlReader.close();
        }
        return list;
    }
}
