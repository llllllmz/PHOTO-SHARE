package com.example.photoshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class RegisterActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    ImageView ivHeadpicture;
    String headpicturePath;
    TextView tvNickname;
    TextView tvAccount;
    TextView tvPassword1;
    TextView tvPassword2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            //查询我们需要的数据
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            headpicturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d("photo111",""+headpicturePath);
//
            ivHeadpicture.setImageBitmap(BitmapFactory.decodeFile(headpicturePath));

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    selectHeadPicture();
                }else{
                    Toast.makeText(this,"你的权限不足！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvAccount= findViewById(R.id.account);
        ImageView ivAccountClear = findViewById(R.id.account_clear);
        tvNickname= findViewById(R.id.nickname);
        ImageView ivNicknameClear = findViewById(R.id.nickname_clear);
        tvPassword1= findViewById(R.id.password_1);
        ImageView ivPasswordClear1 = findViewById(R.id.password_clear_1);
        tvPassword2= findViewById(R.id.password_2);
        ImageView ivPasswordClear2 = findViewById(R.id.password_clear_2);
        TextView tvPswTip = findViewById(R.id.psw_tip);
        ivHeadpicture= findViewById(R.id.headpicture);
        Button btRegister = findViewById(R.id.regist);

        Bmob.initialize(this,"e6736733aa1b7ebe0f8ffc79718d3773");

        tvAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    ivAccountClear.setVisibility(View.VISIBLE);
                }else{
                    ivAccountClear.setVisibility(View.INVISIBLE);
                }

            }
        });

        tvNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    ivNicknameClear.setVisibility(View.VISIBLE);
                }else{
                    ivNicknameClear.setVisibility(View.INVISIBLE);
                }

            }
        });

        tvPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    ivPasswordClear1.setVisibility(View.VISIBLE);
                }else{
                    ivPasswordClear1.setVisibility(View.INVISIBLE);
                }

                if(tvPassword2.getText().toString().equals(s.toString())){
                    tvPswTip.setVisibility(View.INVISIBLE);
                }else {
                    tvPswTip.setVisibility(View.VISIBLE);
                }

            }
        });

        tvPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    ivPasswordClear2.setVisibility(View.VISIBLE);
                }else{
                    ivPasswordClear2.setVisibility(View.INVISIBLE);
                }
                if(tvPassword1.getText().toString().equals(s.toString())){
                    tvPswTip.setVisibility(View.INVISIBLE);
                }else {
                    tvPswTip.setVisibility(View.VISIBLE);
                }

            }
        });


        ivAccountClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAccount.setText("");
            }
        });

        ivNicknameClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNickname.setText("");
            }
        });


        ivPasswordClear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPassword1.setText("");
            }
        });
        ivPasswordClear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPassword2.setText("");
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload(headpicturePath);
            }
        });

        ivHeadpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String []{Manifest.permission.READ_EXTERNAL_STORAGE},1);

                }else{
                    selectHeadPicture();

                }



            }
        });



    }

    void selectHeadPicture(){
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }


    //上传图片到表中
    private void upload(String imgpath){
        final BmobFile bmobFile = new BmobFile(new File(imgpath));

        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    User user = new User();
                    user.setAccount(tvAccount.getText().toString());
                    user.setPassword(tvPassword1.getText().toString());
                    user.setNickname(tvNickname.getText().toString());//当前的用户名
                    user.setHeadpicture(bmobFile);//该用户的头像图片
                    user.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null) {
                                Toast.makeText(RegisterActivity.this, "注册成功:" + s, Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this,"注册失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.w("bbb",bmobFile.getFileUrl());
                    Toast.makeText(RegisterActivity.this,"上传文件成功:" + bmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"上传文件失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}