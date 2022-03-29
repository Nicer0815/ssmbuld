package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuesAns {
    private String readerId;
    private Date askDate;
    private String jobId;
    private String question;
    private String answer;
}
