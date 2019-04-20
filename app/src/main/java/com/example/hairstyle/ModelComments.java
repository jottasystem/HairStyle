package com.example.hairstyle;

public class ModelComments {


    private  String name_comments;
    private  String desc_comments;
    private int rating_star;


    public ModelComments(){

    }


    public ModelComments(String name_comments, String desc_comments, int rating_star) {
        this.name_comments = name_comments;
        this.desc_comments = desc_comments;
        this.rating_star = rating_star;
    }

    public String getName_comments() {
        return name_comments;
    }

    public void setName_comments(String name_comments) {
        this.name_comments = name_comments;
    }

    public String getDesc_comments() {
        return desc_comments;
    }

    public void setDesc_comments(String desc_comments) {
        this.desc_comments = desc_comments;
    }

    public int getRating_star() {
        return rating_star;
    }

    public void setRating_star(int rating_star) {
        this.rating_star = rating_star;
    }
}
