package com.ning.dao;

import com.ning.entity.Record;

import java.util.List;

public interface RecordMapper {

    int addRecord(Record record);

    int updateRecord(Record record);

    int staffReturnBook(Record record);

    List<Record> queryRecordByReaderId(String readerId);
    //未还、续借、逾期
    List<Record> queryUnReturnByReaderId(String readerId);

    List<Record> queryUnReturn();
    //未还
    List<Record> queryContinueByReaderId(String readerId);

    List<Record> queryAllRecord();

}
