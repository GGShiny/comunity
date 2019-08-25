package com.thought.it.service.impl;

import com.thought.it.dto.PaginationDTO;
import com.thought.it.dto.QuestionDTO;
import com.thought.it.exception.CustomizeErrorCode;
import com.thought.it.exception.CustomizeException;
import com.thought.it.mapper.QuestionMapper;
import com.thought.it.mapper.UserMapper;
import com.thought.it.model.Question;
import com.thought.it.model.QuestionExample;
import com.thought.it.model.User;
import com.thought.it.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        pagination.setPagination(page, size,totalCount);

        if (page < 1){
            page = 1;
        }

        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        //offset 每页第一条数据的
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question: questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
//            questionDTO.setId(question.getId());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);
        return pagination;
    }

    @Override
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO pagination = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        pagination.setPagination(page, size,totalCount);

        if (page < 1){
            page = 1;
        }

        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        //offset 每页第一条数据的
        Integer offset = size * (page - 1);
//        List<Question> questions = questionMapper.listMyQuestion(userId, offset, size);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question: questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);


        return pagination;
    }

    @Override
    public QuestionDTO getById(Long id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question questions = questionMapper.selectByPrimaryKey(id);
        if (questions == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        User user = userMapper.selectByPrimaryKey(questions.getCreator());

        BeanUtils.copyProperties(questions, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //插入操作
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
//            questionMapper.create(question);
            questionMapper.insert(question);
        }else {
            //更新操作
            question.setGmtModified(question.getGmtCreate());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(question, questionExample);
            if (updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
