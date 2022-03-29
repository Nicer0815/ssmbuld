package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private String bookId;
    private String readerId;
    private Date commentDate;
    private String content;
}
