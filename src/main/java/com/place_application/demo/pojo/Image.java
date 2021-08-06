package com.place_application.demo.pojo;

public class Image {

    private int img_no;
    private String path;
    private String type;
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getImg_no() {
        return img_no;
    }

    public void setImg_no(int img_no) {
        this.img_no = img_no;
    }

    @Override
    public String toString() {
        return "Image{" +
                "img_no=" + img_no +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }


}
