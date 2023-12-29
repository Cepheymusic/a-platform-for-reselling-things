package ru.skypro.homework.dto;

import java.util.List;

public class CommentsList {
    private int count;
    private List<Comment> results;

    public CommentsList(List<Comment> results) {
        this.count = results.size();
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }
}
