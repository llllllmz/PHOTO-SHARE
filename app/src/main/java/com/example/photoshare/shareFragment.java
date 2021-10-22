package com.example.photoshare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class shareFragment extends Fragment {

    ShareAdapter shareAdapter;
    List<ShareItem> data;

    public shareFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkNeedPermissions();
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share,container,false);
        data = new ArrayList<ShareItem>();
        BmobQuery<ShareItem> query = new BmobQuery<ShareItem>();
        query.order("-createdAt");
        query.findObjects(new FindListener<ShareItem>() {
            @Override
            public void done(List<ShareItem> list, BmobException e) {
                if (e == null) {
                    for (ShareItem ad : list) {
                        data.add(ad);
                        Log.d("shareFragment",""+ad);
                    }
                    if(shareAdapter!=null){
                        shareAdapter.notifyDataSetChanged();
                    }

                } else {
                    Toast.makeText(getContext(), "数据加载失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        shareAdapter = new ShareAdapter(getActivity(), R.layout.share_list_item, data);
        ListView myShareList = rootView.findViewById(R.id.lv_fragment_list);
        myShareList.setAdapter(shareAdapter);


        return rootView;


    }


    private void checkNeedPermissions(){
        //6.0以上需要动态申请权限
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //多个权限一起申请
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }


}