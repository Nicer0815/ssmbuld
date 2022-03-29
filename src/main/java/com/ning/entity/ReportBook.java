package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportBook {
    private String checkId;
    private String bookId;
    private String jobId;
    private Date reportDate;
    private Date disposeDate;
    private int amount;
    private String reason;
    private String state;
}
