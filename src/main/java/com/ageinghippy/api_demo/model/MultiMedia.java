package com.ageinghippy.api_demo.model;

import lombok.Data;
import org.springframework.aop.IntroductionInterceptor;

@Data
public class MultiMedia {
    private Integer rank;
    private String subtype;
    private String caption;
    private String type;
    private String url;

}
