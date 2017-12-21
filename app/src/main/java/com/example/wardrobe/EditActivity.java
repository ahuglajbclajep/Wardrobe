package com.example.wardrobe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class EditActivity extends CameraIntentActivity {
    private Uri oldImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().hide();
        findViewById(R.id.retakeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = createCameraIntent();
                if (intent != null && intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CameraIntentActivity.REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        oldImageUri = getIntent().getParcelableExtra(MainActivity.IMAGE_URI);
        Glide.with(this).load(oldImageUri).into((ImageView) findViewById(R.id.imageView));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraIntentActivity.REQUEST_IMAGE_CAPTURE && canReadImageUri()) {
            Glide.with(this).load(imageUri).into((ImageView) findViewById(R.id.imageView));
            new File(oldImageUri.getPath()).delete();
            oldImageUri = imageUri;
        }
    }
}
