package com.sanyouseki.fwzd.service.impl;

import com.sanyouseki.fwzd.dao.CommentMapper;
import com.sanyouseki.fwzd.entity.Comment;
import com.sanyouseki.fwzd.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment entity) {
        commentMapper.add(entity.getImageId(), entity.getUser(), entity.getComment());
    }

    @Override
    public void deleteCommentById(int id) {
        commentMapper.safeDelete(1, id);
    }

    @Override
    public void updateComment(Comment entity) {
        commentMapper.update(entity.getImageId(),entity.getUser(),entity.getComment());
    }

    @Override
    public Comment getCommentById(int id) {
        return commentMapper.findComment(id);
    }

    @Override
    public List<Comment> getCommentByImageId(int imageId) {
        return commentMapper.findCommentByImageId(imageId);
    }

    @Override
    public List<Comment> getCommentByUser(String user) {
        return commentMapper.findCommentByUser(user);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentMapper.findCommentList();
    }
}
