package com.ning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    private String bookId;
    private String title;
    private String author;
    private Date pubDate;
    private String press;
    private int amount;
    private String subject;
    private float price;
    private String state;
}
