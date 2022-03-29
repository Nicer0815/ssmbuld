package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryInfo {
    private String checkId;
    private String bookId;
    private Date entryDate;
    private int amount;
    private String jobId;
    private float price;
}
