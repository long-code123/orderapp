package com.app.orderfood.models;

public class Category {
    private long Id;
    private String ImagePath;
    private String Name;


    public Category(long Id, String imagePath, String name) {
        this.Id = Id;
        ImagePath = imagePath;
        Name = name;
    }

    public Category() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
