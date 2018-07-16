package com.diegovolantino.mobile.randomuserexercise.activities.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diegovolantino.mobile.randomuserexercise.R;
import com.diegovolantino.mobile.randomuserexercise.data.model.Result;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Diego Pablo Volantino on 12/7/18.
 */

public class GalleryImageAdapter extends BaseAdapter {
    private Context mContext;
    LayoutParams mImageViewLayoutParams;
    ImageLoader imageLoader;
    private int mItemHeight = 0;
    private ArrayList<Result> results;

    public GalleryImageAdapter() {}

    public GalleryImageAdapter(Context c, ArrayList<Result> data) {
        mContext = c;
        imageLoader = ImageLoader.getInstance();
        mImageViewLayoutParams = new RelativeLayout
                .LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        results = data;
    }

    public void setItemHeight(int height) {
        if (height == mItemHeight) {
            return;
        }
        mItemHeight = height;
        mImageViewLayoutParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, mItemHeight);
        notifyDataSetChanged();
    }

    public int getCount() {
        return results.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // Create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.grid_view_cell, null);

        try {

            ImageView imageView = (ImageView) v.findViewById(R.id.iv_thumb);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Result result = results.get(position);
            //Medium
            imageLoader.displayImage(result.getPicture().getMedium(),
                    imageView);

            if (imageView.getLayoutParams().height != mItemHeight) {
                imageView.setLayoutParams(mImageViewLayoutParams);
            }

            if (imageView.getLayoutParams().height != mItemHeight) {
                imageView.setLayoutParams(mImageViewLayoutParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

}