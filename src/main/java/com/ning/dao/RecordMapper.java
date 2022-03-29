package com.ning.dao;

import com.ning.entity.Record;

import java.util.List;

public interface RecordMapper {

    int addRecord(Record record);

    int updateRecord(Record record);

    List<Record> queryRecordByReaderId(String readerId);

    List<Record> queryAllRecord();

}