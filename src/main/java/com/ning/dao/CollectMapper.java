package com.ning.dao;

import com.ning.entity.BookCollect;

import java.util.List;

public interface CollectMapper {

    int addCollect(BookCollect bookCollect);

    int deleteCollect(BookCollect bookCollect);

    List<BookCollect> queryCollectByReaderId(String readerId);

}
