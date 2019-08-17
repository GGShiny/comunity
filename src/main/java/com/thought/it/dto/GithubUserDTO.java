package com.thought.it.dto;

/**
 * Created by Administrator on 2019/8/17.
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
