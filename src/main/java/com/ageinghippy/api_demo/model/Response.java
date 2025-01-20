package com.ageinghippy.api_demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<Doc> docs;
    private Meta meta;
}
