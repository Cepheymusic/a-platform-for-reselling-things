package ru.skypro.homework.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "image_entity")
@Builder
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filePath;

    public ImageEntity(int id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }
}

