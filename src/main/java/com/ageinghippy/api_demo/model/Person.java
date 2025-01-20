package com.ageinghippy.api_demo.model;

import lombok.Data;

@Data
public class Person {

    private String firstname;
    private String middlename;
    private String lastname;
    private String qualifier;
    private String title;
    private String reported;
    private String organisation;
    private Integer rank;
}
