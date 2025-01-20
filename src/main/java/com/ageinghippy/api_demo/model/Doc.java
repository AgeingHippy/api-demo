package com.ageinghippy.api_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Doc {

    @JsonProperty("abstract")
    private String summary;

    @JsonProperty("web_url")
    private String webUrl;

    private String snippet;

    @JsonProperty("lead_paragraph")
    private String leadParagraph;

    private List<MultiMedia> multimedia;

    private String imageUrl;

    private Headline headline;

    @JsonProperty("pub_date")
    private Date publishDate;

    @JsonProperty("news_desk")
    private String newsDesk;
}
