package com.ning.service.impl;

import com.ning.dao.ReaderMapper;
import com.ning.entity.Credentials;
import com.ning.entity.Readers;
import com.ning.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
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
    public int updateCredentials(Credentials credentials) {
        return readerMapper.updateCredentials(credentials);
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
