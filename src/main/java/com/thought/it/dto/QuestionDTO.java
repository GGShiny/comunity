package com.thought.it.dto;

import com.thought.it.model.User;
import lombok.Data;

/**
 * Created by Administrator on 2019/8/19.
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String tag;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
