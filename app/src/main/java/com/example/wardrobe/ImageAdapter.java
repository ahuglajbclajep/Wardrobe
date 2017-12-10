package com.example.wardrobe;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private final int resource;
    private final List<Uri> objects;

    ImageAdapter(Context context, int resource, List<Uri> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Uri getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) (convertView != null ? convertView : View.inflate(context, resource, null));
        Glide.with(context).load(getItem(position)).into(imageView);
        return imageView;
    }
}
