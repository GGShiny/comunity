package com.thought.it.service;

import com.thought.it.dto.PaginationDTO;

/**
 * Created by Administrator on 2019/8/19.
 */
public interface QuestionService {

    PaginationDTO list(Integer page, Integer size);

    PaginationDTO list(Integer id, Integer page, Integer size);
}
