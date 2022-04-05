package com.ning.service;

import com.ning.entity.BookState;

import java.util.List;

public interface BookStateService {
    List<BookState> queryAllState();

    BookState queryStateByBookId(String bookId);
}
