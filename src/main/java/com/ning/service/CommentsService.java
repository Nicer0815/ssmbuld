package com.ning.service;

import com.ning.entity.Comments;

import java.util.List;

public interface CommentsService {
    int addComment(Comments comment);

    List<Comments> queryCommentByBookId(String bookId);

    List<Comments> queryAllComments();
}
