package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    private String bookId;
    private String title;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;
    private String press;
    private int amount;
    private String subject;
    private float price;
    private String state;

}
