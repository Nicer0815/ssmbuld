package com.ning.service;

import com.ning.entity.Record;

import java.util.List;

public interface RecordService {
    int addRecord(Record record);

    int updateRecord(Record record);

    int staffReturnBook(Record record);

    List<Record> queryRecordByReaderId(String readerId);

    List<Record> queryUnReturnByReaderId(String readerId);

    List<Record> queryContinueByReaderId(String readerId);

    List<Record> queryUnReturn();

    List<Record> queryAllRecord();
}
