package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleReader {
    private String readerId;
    private Date reportDate;
    private String jobId;
    private Date disposeDate;
    private String state;
}
