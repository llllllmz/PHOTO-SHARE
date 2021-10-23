package com.example.photoshare;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ZoomControls;

public class DialogImage extends Dialog {
    private ImageView picture;
    private Bitmap btm;
    private Window window = null;

    public DialogImage(Context context, int x, int y, Bitmap bitmap){
        super(context);
        windowDeploy(x,y);
        btm = bitmap;
    }

    public DialogImage(Context context){
        super(context);
    }

    public void onCreate(Bundle savedInstanceState){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_window, null);
        picture = (ImageView)view.findViewById(R.id.dialog_window_image);
        picture.setImageBitmap(btm);

        setContentView(view);
        super.onCreate(savedInstanceState);
    }

    //窗口显示
    public void windowDeploy(int x, int y){
        window = getWindow();
        window.setWindowAnimations(R.style.windowAnimation); //窗口弹出动画
        window.setBackgroundDrawableResource(R.color.black);  //背景为黑色
        WindowManager.LayoutParams wml = window.getAttributes();

        wml.x = x;
        wml.y = y;
        window.setAttributes(wml);
    }

    public void show(){
        setCanceledOnTouchOutside(true); //点击窗口以外的地方会取消窗口
        super.show();
    }

    public void dismiss(){
        super.dismiss();
    }








}
