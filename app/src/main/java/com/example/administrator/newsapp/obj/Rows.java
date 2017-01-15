package com.example.administrator.newsapp.obj;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/14.
 */

public class Rows implements Serializable{
    private String title;

    private String description;

    private String imageHref;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setImageHref(String imageHref){
        this.imageHref = imageHref;
    }
    public String getImageHref(){
        return this.imageHref;
    }
}
