package com.example.photoshare;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

//跟类Pic一样
public class ShareItem extends BmobObject {
    private String nickname;//昵称
    private String username;//账号
    private String picContent; //图片描述
    private int likes;//点赞数
    private BmobFile headPicture;//头像
    private BmobFile sharePicture;//分享图片

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicContent() {
        return picContent;
    }

    public void setPicContent(String picContent) {
        this.picContent = picContent;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public BmobFile getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(BmobFile headPicture) {
        this.headPicture = headPicture;
    }

    public BmobFile getSharePicture() {
        return sharePicture;
    }

    public void setSharePicture(BmobFile sharePicture) {
        this.sharePicture = sharePicture;
    }


}
