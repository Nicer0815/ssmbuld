package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private String readerId;
    private String bookId;
    private Date borrowDate;
    private String state;   //续借，未还，逾期，已还，逾期还书，损坏，丢失
    private Date expectDate;
    private Date returnDate;
    private String title;
    private String author;
}
