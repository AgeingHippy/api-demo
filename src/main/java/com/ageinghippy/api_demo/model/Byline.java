package com.ageinghippy.api_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Byline {

    private String original;
    @JsonProperty("person")
    private List<Person> persons;
    private String organization;
}
