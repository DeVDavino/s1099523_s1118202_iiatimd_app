package com.example.iatiimd_eindoplevering;

public class Idee {
    private String title;
    private String description;
    private String category;

    public Idee(String title, String description, String categorie) {
        this.title = title;
        this.description = description;
        this.category = categorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
