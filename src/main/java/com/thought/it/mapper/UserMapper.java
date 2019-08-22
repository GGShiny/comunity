package com.thought.it.mapper;

import com.thought.it.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2019/8/18.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(id,account_id,name,token,gmt_create,gmt_modified,avatar_url) values(#{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void inser(User user) ;

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
