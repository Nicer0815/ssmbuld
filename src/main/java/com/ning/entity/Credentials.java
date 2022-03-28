package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    private String readerId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date handleDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invalidDate;
    private String password;
    private int score;
    private int borrowNum;
    private String state;
}
