//package com.example.photoshare;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.makeramen.roundedimageview.RoundedImageView;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder>{
//    Bitmap img;
//    private List<Pic> mPicList;
//    private Context mContext;
//    private String imgName;
//    private Dialog dialog;
//    private  ImageView mImageView;
//    private Map<String, Boolean> likeList = new HashMap<>();
//    private int likes = 0;
//    private Map<String, Boolean> share = new HashMap<>();
//    private ViewHolder targetHolder;
//
//    static class ViewHolder extends RecyclerView.ViewHolder{
//        RoundedImageView PicImg; //图片
//        TextView PicContent;    //图片描述
//        RoundedImageView head;  //头像
//        TextView username;  //用户名
//        TextView thumbs;  //点赞数
//        TextView shares;   //分享数
//        TextView comments;  //评论数
//        ImageView thumbup_icon; //点赞图标
//        ImageView share_icon;  //分享图标
//        ImageView comment;    //评论图标
//
//        public ViewHolder(View view){
//            super(view);
//            PicImg = view.findViewById(R.id.iv_image);
//            PicContent = view.findViewById(R.id.tv_content);
//            head = view.findViewById(R.id.iv_head);
//            username = view.findViewById(R.id.tv_username);
//            thumbs = view.findViewById(R.id.tv_likes);
//            thumbup_icon = view.findViewById(R.id.iv_like);
//            share_icon = view.findViewById(R.id.iv_share);
//            shares = view.findViewById(R.id.tv_shares);
//            comment = view.findViewById(R.id.iv_comment);
//            comments = view.findViewById(R.id.tv_comment_num);
//        }
//    }
//
//    public PicAdapter(Context context, List<Pic> PicList){
//        Log.d("Adapter","适配器已创建");
//        mContext = context;
//        mPicList = PicList;
//    }
//
//}
