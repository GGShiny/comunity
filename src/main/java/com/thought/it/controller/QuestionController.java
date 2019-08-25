package com.thought.it.controller;

import com.thought.it.dto.QuestionDTO;
import com.thought.it.mapper.QuestionMapper;
import com.thought.it.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2019/8/22.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id", required = false) String id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(Long.parseLong(id));
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
