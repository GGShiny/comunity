package com.thought.it.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/8/18.
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
