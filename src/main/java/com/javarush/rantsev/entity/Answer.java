package com.javarush.rantsev.entity;

import lombok.Getter;

@Getter
public class Answer {
    private final String text;
    private final boolean wrongAnswer;
    private String wrongAnswerEndText;
    private String backgroundImage;

    public Answer(String text, String wrongAnswerEndText, String backgroundImage) {
        this.text = text;
        this.wrongAnswerEndText = wrongAnswerEndText;
        this.wrongAnswer = true;
        this.backgroundImage = backgroundImage;
    }

    public Answer(String text) {
        this.text = text;
        this.wrongAnswer = false;
    }
}
