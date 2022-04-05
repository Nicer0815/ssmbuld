package com.ning.service.impl;

import com.ning.dao.BookStateMapper;
import com.ning.entity.BookState;
import com.ning.service.BookStateService;

import java.util.List;

public class BookStateServiceImpl implements BookStateService {

    BookStateMapper bookStateMapper;

    public void setBookStateMapper(BookStateMapper bookStateMapper) {
        this.bookStateMapper = bookStateMapper;
    }

    @Override
    public List<BookState> queryAllState() {
        return bookStateMapper.queryAllState();
    }

    @Override
    public BookState queryStateByBookId(String bookId) {
        return bookStateMapper.queryStateByBookId(bookId);
    }
}
