package com.ning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Readers {
    private String readerId;
    private String name;
    private char sex;
    private int phoneNum;
    private Credentials credential;
}
