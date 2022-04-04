package com.ning.service.impl;

import com.ning.dao.BooksCoverMapper;
import com.ning.entity.BooksCover;
import com.ning.service.BooksCoverService;

public class BooksCoverServiceImpl implements BooksCoverService {
    BooksCoverMapper booksCoverMapper;
    @Override
    public int addBooksCover(BooksCover booksCover) {
        return booksCoverMapper.addBooksCover(booksCover);
    }

    @Override
    public BooksCover queryCoverByBookId(String bookId) {
        return booksCoverMapper.queryCoverByBookId(bookId);
    }

    @Override
    public int updateCover(BooksCover booksCover) {
        return booksCoverMapper.updateCover(booksCover);
    }

    public void setBooksCoverMapper(BooksCoverMapper booksCoverMapper) {
        this.booksCoverMapper = booksCoverMapper;
    }
}
