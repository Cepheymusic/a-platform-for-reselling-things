package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsList {
    private int count;
    private List<Ad> results;

    public AdsList(List<Ad> results) {
        this.count = results.size();
        this.results = results;
    }
}
