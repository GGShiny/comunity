package com.thought.it.controller;

import com.thought.it.dto.QuestionDTO;
import com.thought.it.mapper.QuestionMapper;
import com.thought.it.mapper.UserMapper;
import com.thought.it.model.Question;
import com.thought.it.model.User;
import com.thought.it.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/18.
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam("id") Long id,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == ""){
            model.addAttribute("error", "title不能为空");
            return "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error", "description不能为空");
            return "publish";
        }
        if (tag == null || tag == ""){
            model.addAttribute("error", "tag不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
//        questionMapper.create(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public  String edit(@PathVariable("id") Long id,
                        Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        return "publish";
    }
}
