package com.example.photoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mTab1;
    private LinearLayout mTab2;

    //声明四个Tab的ImageButton
    private ImageButton mImg1;
    private ImageButton mImg2;

    //声明四个Tab分别对应的Fragment
    private Fragment mFrag1;
    private Fragment mFrag2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {

        mTab1 = findViewById(R.id.tab1);
        mTab2 = findViewById(R.id.tab2);
        mImg1 = findViewById(R.id.tab_img1);
        mImg2 = findViewById(R.id.tab_img2);

        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        resetImgs(); //先将四个ImageButton置为灰色
        switch (v.getId()) {
            case R.id.tab1:
                selectTab(1);
                break;
            case R.id.tab2:
                selectTab(2);
                break;
        }
    }

    private void resetImgs() {
        mImg1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        mImg2.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }



    private void selectTab(int i) {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        hideFragments(transaction);
        switch (i) {
            //当选中点击的是第一页的Tab时
            case 1:
                //设置第一页的ImageButton为绿色
                mImg1.setBackgroundColor(Color.parseColor("#ff8ced"));
                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFrag1 == null) {
                    mFrag1 = new Fragment_1();
                    transaction.add(R.id.fragment, mFrag1);
                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFrag1);
                }
                break;
            case 2:
                mImg2.setBackgroundColor(Color.parseColor("#ff8ced"));
                if (mFrag2 == null) {
                    mFrag2 = new Fragment_2();
                    transaction.add(R.id.fragment, mFrag2);
                } else {
                    transaction.show(mFrag2);
                }
                break;
        }
        //不要忘记提交事务
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }

    }


}