package com.ning.dao;

import com.ning.entity.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    //增加一本书
    int addBook(Books books);

    //删除一本书
    int deleteBookById(@Param("bookID") int id);

    //更新一本书
    int updateBook(Books books);

    //查询一本书
    Books queryBookById(@Param("bookID")String id);

    //查询全部的书
    List<Books> queryAllBook();

    //通过名字查询书籍
    List<Books> queryBookByName(@Param("bookName")String bookName);
}
