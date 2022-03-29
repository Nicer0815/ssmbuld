package com.ning.dao;

import com.ning.entity.Credentials;
import com.ning.entity.Readers;

import java.util.List;

public interface ReaderMapper {
    //添加一个读者
    int addReader(Readers readers);

    //更新用户信息,不包括读者证
    int updateReader(Readers readers);

    //更新读者证密码、积分、借书数量、状态
    int updateCredentials(Credentials credentials);

    //根据readerId查询一个用户的信息
    Readers queryReaderByReaderId(String readerId);

    //查询所有读者
    List<Readers> queryAllReader();


}
