package com.ning.service;

import com.ning.entity.Books;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    //增加一本书
    int addBook(Books books);

    //删除一本书
    int deleteBookById(int id);

    //更新一本书
    int updateBook(Books books);

    //查询一本书
    Books queryBookById(String id);

    //查询全部的书
    List<Books> queryAllBook();

    //通过名字查询书籍
    List<Books> queryBookByName(String bookName);
}
