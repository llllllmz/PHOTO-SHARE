package com.example.photoshare;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import com.bumptech.glide.Glide;


public class Fragment_2 extends Fragment {


    String username;//账号
    TextView tvNickname;//昵称
    ImageView ivHeadpicture;//头像
    TextView tvUsername;//账号
    BmobFile headpicture;//头像文件
    String nickname;//昵称
//    Button bt;
//    String url;
//    BmobFile icon;
    Button btShare;

    public Fragment_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //下载图片
        User currentUser = BmobUser.getCurrentUser(User.class);
        nickname = currentUser.getNickname();
        username = currentUser.getUsername();
        headpicture = currentUser.getHeadpicture();

//        headpicture.download(new DownloadFileListener() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e != null) {
////                    ivHeadpicture.setImageBitmap(BitmapFactory.decodeFile(s));
//                    url=s;
//                }
//            }
//
//            @Override
//            public void onProgress(Integer integer, long l) {
//
//            }
//        });
//        Glide.with(getContext())
//                                            .load(s)
//                                            .into(ivHeadpicture);
        Log.d("TAG",headpicture.getFileUrl());
        Log.d("TAG",headpicture.getUrl());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_2, container, false);
        tvNickname = rootView.findViewById(R.id.info_nickname);
        tvUsername = rootView.findViewById(R.id.info_username);
        ivHeadpicture = rootView.findViewById(R.id.info_headpicture);
        btShare = rootView.findViewById(R.id.bt_share);
//        bt = rootView.findViewById(R.id.but);
//        tvNickname.setText(" ");
        tvNickname.setText(nickname);
        tvUsername.setText(username);
        Glide.with(getContext()).load(headpicture.getFileUrl()).into(ivHeadpicture);


        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
            }
        });
//        ivHeadpicture.setImageBitmap(BitmapFactory.decodeFile(url));
//        ivHeadpicture.setImageBitmap(BitmapFactory.decodeFile(headpicture);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                BmobQuery<ShareItem> query = new BmobQuery<ShareItem>();
//                query.findObjects(new FindListener<ShareItem>() {
//                    @Override
//                    public void done(List<ShareItem> list, BmobException e) {
//                        if (e == null) {
//                            for (ShareItem ad : list) {
//                                Log.w("nnn", "ad.getName()===" + ad.getUsername() + "HomeFragment.current_user===" + username);
//                                if (ad.getUsername() != null && username.equals(ad.getUsername())) {
////                            Log.w("nnn","ad.getName()==="+ad.getUsername()+"HomeFragment.current_user==="+username);
//                                    icon = ad.getHeadPicture();
//                                    String picContent =ad.getPicContent();
//                                    Log.w("nnn", "hello" + picContent);
//                                    File saveFile = new File(Environment.getExternalStorageDirectory(), icon.getFilename());
//                                    icon.download(saveFile,new DownloadFileListener() {
//
//
//                                        @Override
//                                        public void onProgress(Integer integer, long l) {
//
//                                        }
//                                        @Override
//                                        public void onStart() {
//                                            Toast.makeText(getContext(), "开始下载！！！1", Toast.LENGTH_LONG).show();
//                                        }
//
//                                        @Override
//                                        public void done(String s, BmobException e) {
//                                            if (e == null) {
//                                                //设置圆形头像并显示
//                                                Log.w("nnn", "done" + s);
//                                                ivHeadpicture.setImageBitmap(BitmapFactory.decodeFile(s)); //根据地址解码并显示图片
////                                                 Glide.with(getContext())
////                                            .load(s)
////                                            .into(ivHeadpicture);
//                                            } else {
//                                            }
//                                        }
//                                    });
//                                    String url = icon.getUrl();
////                                    Glide.with(getContext())
////                                            .asBitmap()
////                                            .load("https://blog.csdn.net/yunnansunshitao");
//                                    Glide.with(getContext())
//                                            .load(url)
//                                            .into(ivHeadpicture);
//                                    Toast.makeText(getContext(), "hahahah", Toast.LENGTH_LONG).show();
//                                    break;
//                                }
//
//                            }
//                        } else {
//                            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//
//
//
//            }
//        });


        // Inflate the layout for this fragment

        return rootView;
    }
}