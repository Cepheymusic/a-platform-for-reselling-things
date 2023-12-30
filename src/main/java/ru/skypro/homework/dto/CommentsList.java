package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsList {
    private int count;
    private List<Comment> results;

    public CommentsList(List<Comment> results) {
        this.count = results.size();
        this.results = results;
    }
}
