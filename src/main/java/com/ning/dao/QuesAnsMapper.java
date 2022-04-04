package com.ning.dao;

import com.ning.controller.QuesAnsController;
import com.ning.entity.QuesAns;

import java.util.List;

public interface QuesAnsMapper {

    List<QuesAns> queryQuesByReaderId(String readerId);

    int addQues(QuesAns quesAns);

    int dropQues(QuesAns quesAns);
}
