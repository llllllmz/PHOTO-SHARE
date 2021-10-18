package com.example.photoshare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Fragment_1 extends Fragment {
    private RecyclerView lvPicList;
    private List<Pic> PicData;
    private PicAdapter adapter;
    private Context context = null;
    private View homeview;
    private View picview;

    public Fragment_1() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        homeview = inflater.inflate(R.layout.fragment_1, container, false);
        picview = inflater.inflate(R.layout.list_pic,container,false);


        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    public void initView(){
        lvPicList = homeview.findViewById(R.id.lv_pic_list);
    }

    public void initData(){
        PicData = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this.context,2);
        lvPicList.setLayoutManager(layoutManager);
        adapter = new PicAdapter(context, PicData);
        lvPicList.setAdapter(adapter);
        //getPicData();
    }


}