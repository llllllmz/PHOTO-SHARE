package com.example.photoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    EditText etAccount;
    EditText etPassword;
    private static int REQUEST_TO_REGIST = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==REQUEST_TO_REGIST &&resultCode ==RESULT_OK){
            String account=data.getStringExtra("account");
            etAccount.setText(account);
            etPassword.setText("");


        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        Bmob.initialize(this, "e6736733aa1b7ebe0f8ffc79718d3773");

        Log.d("LoginActivity","onCreate()调用");

        final ImageView ivPwdSwitch = findViewById(R.id.pwd_visibility);
        etPassword = findViewById(R.id.password);
        etAccount = findViewById(R.id.account);
        CheckBox cbRememberPwd = findViewById(R.id.checkBox);
        Button btLogin = findViewById(R.id.login);
        ImageView ivAccountClear = findViewById(R.id.account_clear);
        Button btRegister= findViewById(R.id.register);

        String spFileName = getResources()
                .getString(R.string.shared_preferences_file_name);
        String accountKey = getResources()
                .getString(R.string.login_account_name);
        String passwordKey =  getResources()
                .getString(R.string.login_password);
        String rememberPasswordKey = getResources()
                .getString(R.string.login_remember_password);

        SharedPreferences spFile = getSharedPreferences(
                spFileName,
                MODE_PRIVATE);
        String account = spFile.getString(accountKey, null);
        String password = spFile.getString(passwordKey, null);
        Boolean rememberPassword = spFile.getBoolean(
                rememberPasswordKey,
                false);

        if (account != null && !TextUtils.isEmpty(account)) {
            etAccount.setText(account);
        }

        if (password != null && !TextUtils.isEmpty(password)) {
            etPassword.setText(password);
        }

        cbRememberPwd.setChecked(rememberPassword);


        ivPwdSwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ivPwdSwitch.setImageResource(
                                R.drawable.ic_baseline_visibility_24);
                        etPassword.setInputType(
                                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        break;
                    case MotionEvent.ACTION_UP:
                        ivPwdSwitch.setImageResource(
                                R.drawable.ic_baseline_visibility_off_24);
                        etPassword.setInputType(
                                InputType.TYPE_TEXT_VARIATION_PASSWORD |
                                        InputType.TYPE_CLASS_TEXT);
                        etPassword.setTypeface(Typeface.DEFAULT);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("xxx","移动");
                        break;
                }
                return true;
            }
        });


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(etAccount==null||etAccount.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "请输入账号！", Toast.LENGTH_SHORT).show();
                }else if(etPassword==null||etPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                }else{

                    User user = new User();
                    user.setUsername(etAccount.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.login(new SaveListener<User>() {
                        @Override
                        public void done(User bmobUser, BmobException e) {
                            if (e == null) {
                                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);

                                SharedPreferences.Editor editor = spFile.edit();

                                if (cbRememberPwd.isChecked()) {
                                    String password = etPassword.getText().toString();
                                    String account = etAccount.getText().toString();

                                    editor.putString(accountKey, account);
                                    editor.putString(passwordKey, password);
                                    editor.putBoolean(rememberPasswordKey, true);
                                    editor.apply();
                                } else {
                                    editor.remove(accountKey);
                                    editor.remove(passwordKey);
                                    editor.remove(rememberPasswordKey);
                                    editor.apply();
                                }
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败，请检查用户名和密码是否正确", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });

        etAccount.addTextChangedListener(new TextWatcher() {
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

        ivAccountClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAccount.setText("");
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,REQUEST_TO_REGIST);
            }
        });



    }
}