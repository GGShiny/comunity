package com.thought.it.dto;

import lombok.Data;

/**
 * Created by Administrator on 2019/8/17.
 */
@Data
public class GithubUserDTO {
    /**
     * github上的名称
     */
    private String name;

    /**
     * github上的用户唯一id
     */
    private Long id;

    /**
     * 描述信息
     */
    private String bio;

    /**
     * 用户头像路径
     */
    private String avatarUrl;
}
