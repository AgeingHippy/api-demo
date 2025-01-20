package com.ageinghippy.api_demo.service;

import com.ageinghippy.api_demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ArticleService {

    @Value("${api-key}")
    private String apikey;

    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Value("${searchUrl}")
    private String searchUrl;

    @Value("${staticUrl}")
    private String staticUrl;


    @Autowired
    RestTemplate restTemplate;

    public List<Article> getMostPopular() {
        NytResponse response = restTemplate.getForObject(
                mostPopularUrl + "api-key=" + apikey, NytResponse.class);
        List<Article> results = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            return filterForMediaAndSetThumbnail(response.getResults());
        } else {
            return results;
        }
    }

    public List<Doc> getSearchResults(String searchText) {
        List<Doc> docs;
        String url = UriComponentsBuilder.fromUriString(searchUrl)
                .queryParam("api-key", apikey)
                .queryParam("q", searchText)
                .queryParam("sort", "newest")
                .toUriString();
        ResponseEntity<NytSearchResponse> responseEntity = restTemplate.getForEntity(url, NytSearchResponse.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.hasBody() && responseEntity.getBody() != null) {
            Response response = responseEntity.getBody().getResponse();
            docs = filterForMediaAndSetSubtype(response.getDocs(), "largeHorizontal375");
        } else {
            docs = new ArrayList<>();
        }
        return docs;
    }

    private List<Article> filterForMediaAndSetThumbnail(List<Article> articles) {
        //todo - assumption that Media will contain at least 1 Thumbnail - make more robust
        List<Article> selectedArticles = articles.stream()
                .filter(a -> !a.getMedia().isEmpty()).toList();
        selectedArticles.forEach(art ->
                art.setImageUrl(
                        art.getMedia().getFirst().getMediaMetadata().getFirst().getUrl()
                ));
        return selectedArticles;
    }

    private List<Doc> filterForMediaAndSetSubtype(List<Doc> docs, String subType) {
        List<Doc> docsWithMedia = new ArrayList<>();
        docsWithMedia = docs.stream().filter(d -> !d.getMultimedia().isEmpty()).toList();
        docsWithMedia.forEach(doc -> {
            doc.getMultimedia().stream()
                    .filter(mm -> Objects.equals(mm.getSubtype(), subType))
                    .findFirst()
                    .ifPresent(multi -> doc.setImageUrl(staticUrl + multi.getUrl()));
        });
        return docsWithMedia;
    }
}

