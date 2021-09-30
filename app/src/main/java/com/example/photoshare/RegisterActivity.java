package com.example.photoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView tvAccount= findViewById(R.id.account);
        ImageView ivAccountClear = findViewById(R.id.account_clear);
        TextView tvNickname= findViewById(R.id.nickname);
        ImageView ivNicknameClear = findViewById(R.id.nickname_clear);
        TextView tvPassword1= findViewById(R.id.password_1);
        ImageView ivPasswordClear1 = findViewById(R.id.password_clear_1);
        TextView tvPassword2= findViewById(R.id.password_2);
        ImageView ivPasswordClear2 = findViewById(R.id.password_clear_2);
        TextView tvPswTip = findViewById(R.id.psw_tip);

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
    }
}