package ru.skypro.homework.dto;

import java.util.List;

public class AdsList {
    private int count;
    private List<Ad> results;

    public AdsList(List<Ad> results) {
        this.count = results.size();
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Ad> getResults() {
        return results;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }
}
