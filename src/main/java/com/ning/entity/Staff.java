package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private String jobId;
    private String personId;
    private String name;
    private String password;
    private String rank;
    private char sex;
    private int phoneNum;
}
