package com.thought.it.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/20.
 */
@Data
public class PaginationDTO {
    /**
     * 每页返回的数据
     */
    private List<QuestionDTO> questions;

    /**
     * 是否有跳转到上一页按钮
     */
    private Boolean hasPrevious;

    /**
     * 是否有跳转到首页的按钮
     */
    private Boolean hasFirstPage;

    /**
     * 是否有跳转到下一页的按钮
     */
    private Boolean hasNext;

    /**
     * 是否有跳转到尾页的按钮
     */
    private Boolean hasEndPage;

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 返回按钮列表
     */
    private List<Integer> pages = new ArrayList<>();

    /**
     * 总的页数
     */
    private Integer totalPage;

    public void setPagination(Integer page, Integer size, Integer totalCount) {

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1){
            page = 1;
        }

        if (page > totalPage){
            page = totalPage;
        }

        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++){
            if (page - i > 0){
                pages.add(0,page -i);
            }

            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }

        //当前页为第一页时不显示跳转到上一页的按钮
        if (page == 1) {
            hasPrevious = false;
        } else {
            hasPrevious = true;
        }

        //当前页为最后一页时不显示跳转到下一页的按钮
        if (page == totalPage) {
            hasNext = false;
        } else {
            hasNext = true;
        }

        //pages 中包含第一页时不展示跳转首页的按钮
        if (pages.contains(1)) {
            hasFirstPage = false;
        } else {
            hasFirstPage = true;
        }

        //pages 中包含最后一页时不展示跳转尾页的按钮
        if (pages.contains(totalPage)){
            hasEndPage = false;
        }else {
            hasEndPage = true;
        }
    }
}
