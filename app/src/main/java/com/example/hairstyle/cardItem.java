package com.example.hairstyle;

public class cardItem {
    int background;
    String title;
    int imgCard;

    public cardItem(){

    }

    public cardItem(  String title, int imgCard){
        this.imgCard = imgCard;
        this.title = title;
    }

    public int getBackground(){
        return background;

    }

    public String getTitle(){
        return title;

    }

    public int getImgCard(){
        return imgCard;

    }

    public void setBackground(int background){
        this.background = background;

    }

    public void setTitle(String title){
        this.title = title;

    }

    public void setImgCard(int imgCard){
        this.imgCard = imgCard;

    }


}
