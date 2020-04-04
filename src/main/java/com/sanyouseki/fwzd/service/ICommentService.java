package com.sanyouseki.fwzd.service;

import com.sanyouseki.fwzd.entity.Comment;

import java.util.List;

public interface ICommentService {
    void addComment(Comment entity);
    void deleteCommentById(int id);
    void updateComment(Comment entity);
    Comment getCommentById(int id);
    List<Comment> getCommentByImageId(int imageId);
    List<Comment> getCommentByUser(String user);
    List<Comment> getAllComments();
}
