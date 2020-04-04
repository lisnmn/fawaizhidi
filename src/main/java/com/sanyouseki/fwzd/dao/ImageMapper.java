package com.sanyouseki.fwzd.dao;

import com.sanyouseki.fwzd.entity.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert("insert into image(uploader, name, url, thumb_url) values(#{uploader}, #{name}, #{url}, #{thumb_url})")
    int add(@Param("uploader") String uploader, @Param("name") String name, @Param("url") String url, @Param("thumb_url") String thumb_url);

    @Update("update image set uploader = #{uploader}, name = #{name}, url = #{url}, thumb_url = #{thumb_url}, rank = #{rank} where id = #{id}")
    int update(@Param("uploader") String uploader, @Param("name") String name, @Param("url") String url, @Param("thumb_url") String thumb_url, @Param("rank") String rank, @Param("id") int id);

    @Update("update image set del = #{del} where id = #{id}")
    int safeDelete(@Param("del") int del, @Param("id") int id);

    @Delete("delete from image where id = #{id}")
    int delete(int id);

    @Select("select * from image where id = #{id} and del = 0")
    Image findImage(@Param("id") int id);

    @Select("select * from image where uploader = #{uploader} and del = 0 ORDER BY upload_time DESC")
    List<Image> findImageByUploader(@Param("uploader") String uploader);

    @Select("select * from image where url = #{url} and and del = 0 ORDER BY upload_time DESC")
    List<Image> findImageByUrl(@Param("url") String url);

    @Select("select * from image where del = 0 ORDER BY upload_time DESC")
    List<Image> findImageList();
}
