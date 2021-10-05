package com.example.photoshare;

import android.graphics.Bitmap;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {
//    private String account;
    private String nickname;
//    private String password;
    private BmobFile headpicture;

//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public BmobFile getHeadpicture() {
        return headpicture;
    }

    public void setHeadpicture(BmobFile headpicture) {
        this.headpicture = headpicture;
    }
}
