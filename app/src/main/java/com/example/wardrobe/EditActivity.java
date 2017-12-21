package com.example.wardrobe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class EditActivity extends CameraIntentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Uri imageUri = getIntent().getParcelableExtra(MainActivity.IMAGE_URI);

        Glide.with(this).load(imageUri).into((ImageView) findViewById(R.id.imageView));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
