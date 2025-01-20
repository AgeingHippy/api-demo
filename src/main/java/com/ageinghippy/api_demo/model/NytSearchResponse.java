package com.ageinghippy.api_demo.model;

import lombok.Data;

@Data
public class NytSearchResponse {

    private String status;
    private String copyright;
    private Response response;

}
