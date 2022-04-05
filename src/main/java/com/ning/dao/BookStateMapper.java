package com.ning.dao;

import com.ning.entity.BookState;

import java.util.List;

public interface BookStateMapper {
    List<BookState> queryAllState();

    BookState queryStateByBookId(String bookId);

}
