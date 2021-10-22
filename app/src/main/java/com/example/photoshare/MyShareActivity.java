package com.example.photoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MyShareActivity extends AppCompatActivity {

    private static String TAG = "MyShareActivity";
    ShareAdapter shareAdapter;
    List<ShareItem> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        setTitle("我发布的");
        data = new ArrayList<ShareItem>();
        BmobQuery<ShareItem> query = new BmobQuery<ShareItem>();
        query.order("-createdAt");
        query.addWhereEqualTo("user",BmobUser.getCurrentUser(User.class));
        query.include("user");
        query.findObjects(new FindListener<ShareItem>() {
            @Override
            public void done(List<ShareItem> list, BmobException e) {
                if (e == null) {
//                    for (ShareItem ad : list) {
//                        _User currentUser = BmobUser.getCurrentUser(_User.class);
//                        if (ad.getUsername() != null && currentUser.getUsername().equals(ad.getUsername())) {
//                            data.add(ad);
//                            Log.d(TAG, "" + ad.getSharePicture().getUrl());
//                        }
//
//                    }
                    for(ShareItem ll :list){
                        data.add(ll);
                    }
                    shareAdapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(MyShareActivity.this, "获取失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        shareAdapter = new ShareAdapter(MyShareActivity.this, R.layout.share_list_item, data);
        ListView myShareList = findViewById(R.id.lv_myshare_list);
        myShareList.setAdapter(shareAdapter);

    }
}