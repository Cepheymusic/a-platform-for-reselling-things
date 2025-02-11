package ru.skypro.homework.dto;

import java.util.List;

public class CommentsList {
    private int count;
    private List<Comment> result;

    public CommentsList(int count, List<Comment> result) {
        this.count = count;
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResult() {
        return result;
    }

    public void setResult(List<Comment> result) {
        this.result = result;
    }
}
