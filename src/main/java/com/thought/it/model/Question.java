package com.thought.it.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/8/19.
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String tag;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
