package com.ning.service.impl;

import com.ning.dao.CommentsMapper;
import com.ning.entity.Comments;
import com.ning.service.CommentsService;

import java.util.Date;
import java.util.List;

public class CommentsServiceImpl implements CommentsService {

    CommentsMapper commentsMapper;

    public void setCommentsMapper(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public int addComment(Comments comment) {
        comment.setCommentDate(new Date());
        return commentsMapper.addComment(comment);
    }

    @Override
    public List<Comments> queryCommentByBookId(String bookId) {
        return commentsMapper.queryCommentByBookId(bookId);
    }

    @Override
    public List<Comments> queryAllComments() {
        return commentsMapper.queryAllComments();
    }
}
