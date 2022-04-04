package com.ning.service.impl;

import com.ning.dao.RecordMapper;
import com.ning.entity.Record;
import com.ning.service.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {
    RecordMapper recordMapper;
    @Override
    public int addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    @Override
    public int updateRecord(Record record) {
        return recordMapper.updateRecord(record);
    }

    @Override
    public List<Record> queryRecordByReaderId(String readerId) {
        return recordMapper.queryRecordByReaderId(readerId);
    }

    @Override
    public List<Record> queryAllRecord() {
        return recordMapper.queryAllRecord();
    }

    @Override
    public List<Record> queryUnReturnByReaderId(String readerId) {
        return recordMapper.queryUnReturnByReaderId(readerId);
    }

    @Override
    public List<Record> queryContinueByReaderId(String readerId) {
        return recordMapper.queryContinueByReaderId(readerId);
    }

    public void setRecordMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }
}
