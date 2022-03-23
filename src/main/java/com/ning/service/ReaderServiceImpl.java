package com.ning.service;

import com.ning.dao.ReaderMapper;
import com.ning.pojo.Readers;

import java.util.List;

public class ReaderServiceImpl implements ReaderService{
    private ReaderMapper readerMapper;

    @Override
    public int addReader(Readers readers) {
        return readerMapper.addReader(readers);
    }

    @Override
    public int updateReader(Readers readers) {
        return readerMapper.updateReader(readers);
    }

    @Override
    public Readers queryReaderByReaderId(String readerId) {
        return readerMapper.queryReaderByReaderId(readerId);
    }

    @Override
    public List<Readers> queryAllReader() {
        return readerMapper.queryAllReader();
    }

    public void setReaderMapper(ReaderMapper readerMapper) {
        this.readerMapper = readerMapper;
    }
}
