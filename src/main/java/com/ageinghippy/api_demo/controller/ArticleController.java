package com.ageinghippy.api_demo.controller;

import com.ageinghippy.api_demo.model.Doc;
import com.ageinghippy.api_demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articleList", articleService.getMostPopular());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String searchText, Model model) {
       model.addAttribute("searchText",searchText);
       if (searchText != null) {
           model.addAttribute("docList", articleService.getSearchResults(searchText));
       }
       else {
           model.addAttribute("docList", new ArrayList<Doc>());
       }

       return "search-results";
    }
}

