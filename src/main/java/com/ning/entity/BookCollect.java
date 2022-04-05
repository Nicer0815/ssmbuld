package com.ning.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCollect {
    private String readerId;
    private String bookId;
    private Date collectDate;

    //表内没有的的属性
    private String title;
    private String author;

    public BookCollect(String bookId, String readerId, Date date) {
        this.bookId=bookId;
        this.readerId=readerId;
        this.collectDate=date;
    }
}
