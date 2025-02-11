package ru.skypro.homework.dto;

public class Ad {
    private int authorId;
    private String image;
    private int pkAdId;
    private int price;
    private String title;

    public Ad(int authorId, String image, int pkAdId, int price, String title) {
        this.authorId = authorId;
        this.image = image;
        this.pkAdId = pkAdId;
        this.price = price;
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPkAdId() {
        return pkAdId;
    }

    public void setPkAdId(int pkAdId) {
        this.pkAdId = pkAdId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
