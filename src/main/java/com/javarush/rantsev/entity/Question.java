package com.javarush.rantsev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class Question {
    private int id;
    private String text;
    private String backgroundImage;
    private List<Answer> answerList;
}
