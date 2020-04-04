package com.sanyouseki.fwzd.dao;

import com.sanyouseki.fwzd.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(image_id, user, comment) values(#{image_id}, #{user}, #{comment})")
    int add(@Param("image_id") int image_id, @Param("user") String user, @Param("comment") String comment);

    @Update("update comment set image_id = #{image_id}, user = #{user}, comment = #{comment}")
    int update(@Param("image_id") int image_id, @Param("user") String user, @Param("comment") String comment);

    @Update("update comment set del = #{del} where id = #{id}")
    int safeDelete(@Param("del") int del, @Param("id") int id);

    @Delete("delete from comment where id = #{id}")
    int delete(int id);

    @Select("select * from comment where id = #{id} and del = 0")
    Comment findComment(@Param("id") int id);

    @Select("select * from comment where image_id = #{image_id} and del = 0 ORDER BY time DESC")
    List<Comment> findCommentByImageId(@Param("image_id") int image_id);

    @Select("select * from comment where user = #{user} and del = 0 ORDER BY time DESC")
    List<Comment> findCommentByUser(@Param("user") String user);

    @Select("select * from comment where del = 0 ORDER BY upload_time DESC")
    List<Comment> findCommentList();
}
