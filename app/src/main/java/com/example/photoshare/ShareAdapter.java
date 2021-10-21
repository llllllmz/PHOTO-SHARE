package com.example.photoshare;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//适配器ShareAdapter
public class ShareAdapter extends ArrayAdapter<ShareItem> {

        private List<ShareItem> mData;
        private Context mContext;
        private int resourceId;


        public ShareAdapter(Context context,
                            int resourceId, List<ShareItem> data) {
            super(context, resourceId, data);
            this.mContext = context;
            this.mData = data;
            this.resourceId = resourceId;
        }

        @Override
        public View getView(int position,
                            View convertView, ViewGroup parent) {
            ShareItem share = getItem(position);
            View view ;

            final ViewHolder vh;

            if (convertView == null) {
                view = LayoutInflater.from(getContext())
                        .inflate(resourceId, parent, false);

                vh = new ViewHolder();
                vh.tvNickname  = view.findViewById(R.id.tv_nickname);
                vh.ivHead = view.findViewById(R.id.iv_head);
                vh.ivImage = view.findViewById(R.id.iv_image);
                vh.tvContent = view.findViewById(R.id.tv_content);
                vh.tvLikes = view.findViewById(R.id.tv_likes);
                vh.tvDate = view.findViewById(R.id.tv_publish_date);
                vh.ivSave = view.findViewById(R.id.iv_save);
                view.setTag(vh);
            } else {
                view = convertView;
                vh = (ViewHolder) view.getTag();
            }

            vh.tvNickname.setText(share.getNickname());
            vh.tvContent.setText(share.getPicContent());
            vh.tvLikes.setText(share.getLikes().toString());
            vh.tvDate.setText(share.getCreatedAt());
            Glide.with(mContext).load(share.getHeadPicture().getUrl())
                    .into(vh.ivHead);
            Glide.with(mContext).load(share.getSharePicture().getUrl())
                    .into(vh.ivImage);


            vh.ivSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //TODO 下载图片资源
                }
            });





            return view;
        }

        class ViewHolder {
            TextView tvNickname;
            TextView tvContent;
            ImageView ivHead;
            ImageView ivImage;
            TextView tvLikes;
            TextView tvDate;
            ImageView ivSave;

        }

//        //Bitmap转换本地为文件
//    public void saveImageToGallery(Bitmap bmp) {
//        if (bmp==null){
//            Log.e("TAG","bitmap---为空");
//            return ;
//        }
//        String galleryPath= Environment.getExternalStorageDirectory()
//                + File.separator + Environment.DIRECTORY_DCIM
//                +File.separator+"Camera"+File.separator;
//        String fileName = +System.currentTimeMillis() + ".jpg";
//        Log.d("path",galleryPath);
//        File file = new File(galleryPath, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            //通过io流的方式来压缩保存图片
//            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
//            fos.flush();
//            fos.close();
//            //保存图片后发送广播通知更新数据库
//            Uri uri = Uri.fromFile(file);
//            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
//            if (isSuccess) {
//                Log.e("TAG","图片保存成功 保存在:" + file.getPath());
//                Toast.makeText(getContext(), "图片保存成功 保存在:" + file.getPath(), Toast.LENGTH_SHORT).show();
//            } else {
//                Log.e("TAG","图片保存失败");
//                Toast.makeText(getContext(), "图片保存失败", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            Log.e("TAG","保存图片找不到文件夹");
//            Toast.makeText(getContext(), "保存图片找不到文件夹", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }



}
