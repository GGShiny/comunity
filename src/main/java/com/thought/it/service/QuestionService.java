package com.thought.it.service;

import com.thought.it.dto.PaginationDTO;
import com.thought.it.dto.QuestionDTO;
import com.thought.it.model.Question;

/**
 * Created by Administrator on 2019/8/19.
 */
public interface QuestionService {

    PaginationDTO list(Integer page, Integer size);

    PaginationDTO list(Long id, Integer page, Integer size);

        QuestionDTO getById(Long id);

    void createOrUpdate(Question question);
}
