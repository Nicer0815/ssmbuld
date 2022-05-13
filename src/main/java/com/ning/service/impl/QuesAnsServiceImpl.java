package com.ning.service.impl;

import com.ning.dao.QuesAnsMapper;
import com.ning.entity.QuesAns;
import com.ning.service.QuesAnsService;

import java.util.List;

public class QuesAnsServiceImpl implements QuesAnsService {

    QuesAnsMapper quesAnsMapper;

    @Override
    public List<QuesAns> queryQuesByReaderId(String readerId) {
        return quesAnsMapper.queryQuesByReaderId(readerId);
    }

    @Override
    public int addQues(QuesAns quesAns) {
        return quesAnsMapper.addQues(quesAns);
    }

    @Override
    public int dropQues(QuesAns quesAns) {
        return quesAnsMapper.dropQues(quesAns);
    }

    @Override
    public int quesAnsReply(QuesAns quesAns) {
        return quesAnsMapper.quesAnsReply(quesAns);
    }

    public void setQuesAnsMapper(QuesAnsMapper quesAnsMapper) {
        this.quesAnsMapper = quesAnsMapper;
    }

    @Override
    public List<QuesAns> queryAllQues() {
        return quesAnsMapper.queryAllQues();
    }

    @Override
    public List<QuesAns> queryUnsolvedQues() {
        return quesAnsMapper.queryUnsolvedQues();
    }

    @Override
    public List<QuesAns> querySolvedQues() {
        return quesAnsMapper.querySolvedQues();
    }
}
