package ru.skypro.homework.dto;

public class CreateOrUpdateComment {
    private String text;

    public CreateOrUpdateComment(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
