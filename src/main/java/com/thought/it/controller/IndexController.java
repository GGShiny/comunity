package com.thought.it.controller;

import com.thought.it.dto.PaginationDTO;
import com.thought.it.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2019/8/17.
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1" ,required = false) Integer page,
                        @RequestParam(name = "siez", defaultValue = "5", required = false) Integer size){
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
