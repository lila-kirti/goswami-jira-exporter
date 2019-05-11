package ru.bvg.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.bvg.model.GooswamiRuMediaResponse;

@Service
public class GoswamiRuService {

    @Value("${goswamiru.url}")
    private String goswamiruUrl;

    public GooswamiRuMediaResponse getBooks(int offset, int size) {
        HttpEntity<GoswamiRuCriteria> request = new HttpEntity<>(new GoswamiRuCriteria("book",offset, size));
        return new RestTemplate()
                .exchange(goswamiruUrl + "/catalogue", HttpMethod.POST, request, GooswamiRuMediaResponse.class).getBody();
    }

    public GooswamiRuMediaResponse getArticles(int offset, int size) {
        HttpEntity<GoswamiRuCriteria> request = new HttpEntity<>(new GoswamiRuCriteria("article",offset, size));
        return new RestTemplate()
                .exchange(goswamiruUrl + "/catalogue", HttpMethod.POST, request, GooswamiRuMediaResponse.class).getBody();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class GoswamiRuCriteria {
        @JsonProperty("content_type")
        private String contentType;
        private int page = 1;
        private int size = 10;
    }
}
