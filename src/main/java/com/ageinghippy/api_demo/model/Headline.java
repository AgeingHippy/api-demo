package com.ageinghippy.api_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Headline {
    private String main;
    private String kicker;

    @JsonProperty("content_kicker")
    private String contentKicker;

    @JsonProperty("print_headline")
    private String print_headline;

//    private String name;
//    private String seo;
//    private String sub;

}
