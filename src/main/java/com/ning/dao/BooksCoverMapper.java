package com.ning.dao;

import com.ning.entity.BooksCover;

import java.util.List;

public interface BooksCoverMapper {
    int addBooksCover(BooksCover booksCover);

    BooksCover queryCoverByBookId(String bookId);

    int updateCover(BooksCover booksCover);

}
