package com.ning.service;

import com.ning.entity.QuesAns;

import java.util.List;

public interface QuesAnsService {
    List<QuesAns> queryQuesByReaderId(String readerId);

    int addQues(QuesAns quesAns);

    int dropQues(QuesAns quesAns);

    List<QuesAns> queryAllQues();

    List<QuesAns> queryUnsolvedQues();
}
