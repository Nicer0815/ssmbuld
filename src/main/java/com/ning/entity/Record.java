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
    private String state;
    private Date expectDate;
    private Date returnDate;

}
