package com.thought.it.mapper;

import com.thought.it.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2019/8/18.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(id,account_id,name,token,gmt_create,gmt_modified) values(#{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void inser(User user) ;
}
