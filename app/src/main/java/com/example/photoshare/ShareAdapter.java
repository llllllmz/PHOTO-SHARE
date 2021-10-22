package com.example.photoshare;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
                vh.ivShare = view.findViewById(R.id.iv_share);
                vh.ivLikes = view.findViewById(R.id.iv_like);
                view.setTag(vh);
            } else {
                view = convertView;
                vh = (ViewHolder) view.getTag();
            }

            User user = share.getUser();
            Log.d("shareAdapter",user.getNickname()+","+user.getHeadpicture());
            vh.tvNickname.setText(user.getNickname());
            vh.tvContent.setText(share.getPicContent());
            vh.tvLikes.setText(share.getLikes().toString());
            vh.tvDate.setText(share.getCreatedAt());
            Glide.with(mContext).load(user.getHeadpicture().getUrl())
                    .into(vh.ivHead);
            Glide.with(mContext).load(share.getSharePicture().getUrl())
                    .into(vh.ivImage);

            vh.ivLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 点赞
                    Log.d("shareAdapter","点赞："+share.toString());
                }
            });

            vh.ivSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //TODO 下载图片资源
                    Log.d("shareAdapter","下载："+share.getSharePicture().getUrl());
                }
            });

            vh.ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 分享图片     getUrl()和getFileUrl()结果一样
                    Log.d("shareAdapter","分享："+share.getSharePicture().getFileUrl());
                }
            });

            vh.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 查看大图片
                    Log.d("shareAdapter","查看大图："+share.getSharePicture().getFileUrl());
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
            ImageView ivLikes;
            TextView tvDate;
            ImageView ivSave;
            ImageView ivShare;

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
