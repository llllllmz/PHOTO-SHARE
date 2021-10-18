package com.example.photoshare;

import android.media.Image;
import android.widget.ImageView;

public class Pic {
    private String username;
    private String picContent; //图片描述
    private int likes;
    private Boolean isLike;
    private ImageView head;
    String getUsername(){
        return username;
    }
    public void setUsername(String username) { this.username = username; }
    //public getHead() { return head; }

}
