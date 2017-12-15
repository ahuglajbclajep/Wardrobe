package com.example.wardrobe;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

abstract public class CameraIntentActivity extends AppCompatActivity {
    protected Uri imageUri;

    @Override
    abstract protected void onActivityResult(int requestCode, int resultCode, Intent data);

    protected Intent createCameraIntent() {
        File file;
        try {
            file = createImageFile();
        } catch (IOException e) {
            return null;
        }
        imageUri = Uri.fromFile(file);
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
    }

    protected boolean canDeleteEmptyImage() {
        File imageFile = new File(imageUri.getPath());
        if (imageFile.length() == 0) {
            imageUri = null;
            return imageFile.delete();
        }
        return true;
    }

    private File createImageFile() throws IOException {
        File baseDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile("image", ".jpg", baseDir);
    }
}
