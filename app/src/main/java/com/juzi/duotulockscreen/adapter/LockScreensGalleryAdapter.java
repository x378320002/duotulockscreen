package com.juzi.duotulockscreen.adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.juzi.duotulockscreen.R;
import com.juzi.duotulockscreen.bean.LockScreenImgBean;
import com.juzi.duotulockscreen.util.ImageLoaderManager;

import java.util.ArrayList;

public class LockScreensGalleryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<LockScreenImgBean> mData;
    private int mImgHeight;
    private int mImgWidth;

    public LockScreensGalleryAdapter(Context context, ArrayList<LockScreenImgBean> data) {
        mContext = context;
        mData = data;

        mImgWidth = context.getResources().getDimensionPixelSize(R.dimen.grid_img_width);
        mImgHeight = context.getResources().getDimensionPixelSize(R.dimen.grid_img_height);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == mData.size()) {
//            return 1;
//        }
//        return 0;
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        boolean shouldInitBitmap;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.activity_mylockscreenimgs_grid_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            shouldInitBitmap = true;
        } else {
            holder = (ViewHolder) convertView.getTag();
            shouldInitBitmap = holder.oldPos != position;
        }

        holder.oldPos = position;

        String url = mData.get(position).getImg_url();
        if (shouldInitBitmap) {
            holder.ivitemcontent.setImageBitmap(null);
        }
        ImageLoaderManager.getInstance().displayImage("file://" + url, holder.ivitemcontent
                , mImgWidth, mImgHeight, true);
        return convertView;
    }

    private class ViewHolder {
        public int oldPos;
        public final ImageView ivitemcontent;
        public final View root;

        public ViewHolder(View root) {
            ivitemcontent = (ImageView) root.findViewById(R.id.iv_item_content);
            this.root = root;
        }
    }

    public Point getItemWH() {
        return new Point(mImgWidth, mImgHeight);
    }
}
