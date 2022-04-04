package com.ning.service.impl;

import com.ning.dao.CollectMapper;
import com.ning.entity.BookCollect;
import com.ning.service.CollectService;

import java.util.List;

public class CollectServiceImpl implements CollectService {

    private CollectMapper collectMapper;
    @Override
    public int addCollect(BookCollect bookCollect) {
        return collectMapper.addCollect(bookCollect);
    }

    @Override
    public int deleteCollect(BookCollect bookCollect) {
        return collectMapper.deleteCollect(bookCollect);
    }

    @Override
    public List<BookCollect> queryCollectByReaderId(String readerId) {
        return collectMapper.queryCollectByReaderId(readerId);
    }
    public void setCollectMapper(CollectMapper collectMapper) {
        this.collectMapper = collectMapper;
    }
}
