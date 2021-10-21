package com.example.photoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        setTitle("我发布的");
        List<ShareItem> data = new ArrayList<ShareItem>();
        BmobQuery<ShareItem> query = new BmobQuery<ShareItem>();
        query.order("-createdAt");
        query.findObjects(new FindListener<ShareItem>() {
            @Override
            public void done(List<ShareItem> list, BmobException e) {
                if (e == null) {
                    for (ShareItem ad : list) {
                        User currentUser = BmobUser.getCurrentUser(User.class);
                        if (ad.getUsername() != null && currentUser.getUsername().equals(ad.getUsername())) {
                            data.add(ad);
                            Log.d(TAG, "" + ad.getSharePicture().getUrl());
                        }

                    }
                    ShareAdapter shareAdapter = new ShareAdapter(MyShareActivity.this, R.layout.share_list_item, data);
                    ListView myShareList = findViewById(R.id.lv_myshare_list);
                    myShareList.setAdapter(shareAdapter);
                } else {
                    Toast.makeText(MyShareActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}