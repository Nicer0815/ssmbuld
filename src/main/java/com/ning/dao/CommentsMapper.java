package com.ning.dao;

import com.ning.entity.Comments;

import java.util.List;

public interface CommentsMapper {
    int addComment(Comments comment);

    List<Comments> queryCommentByBookId(String bookId);

    List<Comments> queryAllComments();
}
