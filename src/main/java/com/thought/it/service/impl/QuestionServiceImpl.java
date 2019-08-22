package com.thought.it.service.impl;

import com.thought.it.dto.PaginationDTO;
import com.thought.it.dto.QuestionDTO;
import com.thought.it.mapper.QuestionMapper;
import com.thought.it.mapper.UserMapper;
import com.thought.it.model.Question;
import com.thought.it.model.User;
import com.thought.it.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/19.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO pagination = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        pagination.setPagination(page, size,totalCount);

        if (page < 1){
            page = 1;
        }

        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        //offset 每页第一条数据的
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question: questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);


        return pagination;
    }

    @Override
    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO pagination = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        pagination.setPagination(page, size,totalCount);

        if (page < 1){
            page = 1;
        }

        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        //offset 每页第一条数据的
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listMyQuestion(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question: questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);


        return pagination;
    }
}
