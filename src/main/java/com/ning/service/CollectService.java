package com.ning.service;

import com.ning.entity.BookCollect;

import java.util.List;

public interface CollectService {
    int addCollect(BookCollect bookCollect);

    int deleteCollect(BookCollect bookCollect);

    List<BookCollect> queryCollectByReaderId(String readerId);
}
