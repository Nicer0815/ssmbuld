package com.ning.service;

import com.ning.entity.BooksCover;

public interface BooksCoverService {


    int addBooksCover(BooksCover booksCover);

    BooksCover queryCoverByBookId(String bookId);

    int updateCover(BooksCover booksCover);

}
