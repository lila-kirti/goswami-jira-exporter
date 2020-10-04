package ru.bvg.service;


//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bvg.model.JiraIssueResponse;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class JiraService {

    @Value("${jira.url}")
    private String jiraUrl;

    @Value("${jira.login}")
    private String jiraLogin;

    @Value("${jira.password}")
    private String jiraPassword;

    public JiraIssueResponse getIssues(int offset, int size) {
        StringBuilder url = new StringBuilder(jiraUrl);
        url.append("/rest/api/2/search?")
                .append("startAt=").append(offset)
                .append("&maxResults=").append(size)
                .append("&jql=type=\"Обработанная лекция\" and \"Не публиковать на сайте\" is EMPTY and resolution = Разрешен ORDER BY \"Дата лекции\"");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate.exchange(url.toString(), HttpMethod.GET,
                new HttpEntity(createHeaders()), JiraIssueResponse.class).getBody();


    }

    public JiraIssueResponse getDailyShlokaIssues(int offset, int size) {
        StringBuilder url = new StringBuilder(jiraUrl);
        url.append("/rest/api/2/search?")
                .append("startAt=").append(offset)
                .append("&maxResults=").append(size)
                .append("&jql=type=\"Обработанная лекция\" and labels=\"ни_дня_без_шлоки\" ORDER BY summary ASC");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate.exchange(url.toString(), HttpMethod.GET,
                new HttpEntity(createHeaders()), JiraIssueResponse.class).getBody();

    }

    public JiraIssueResponse getByDate(Date date) {
        StringBuilder url = new StringBuilder(jiraUrl);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        url.append("/rest/api/2/search?")
                .append("jql=type=\"Обработанная лекция\" and \"Дата лекции\"=")
                .append(sdf.format(date));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate.exchange(url.toString(), HttpMethod.GET,
                new HttpEntity(createHeaders()), JiraIssueResponse.class).getBody();
    }

    HttpHeaders createHeaders() {
        return new HttpHeaders() {{
            String auth = jiraLogin + ":" + jiraPassword;
            String encodedAuth = Base64.encode(
                    auth.getBytes(Charset.forName("UTF-8")));
            String authHeader = "Basic " + encodedAuth;
            set("Authorization", authHeader);
        }};
    }
}
