package com.example.wardrobe;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class Util {
    public static List<Uri> getImageList(Context context) {
        File baseDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        List<Uri> uris = new LinkedList<>();
        for (File file : Arrays.asList(baseDir.listFiles())) {
            uris.add(Uri.fromFile(file));
        }
        return uris;
    }
}
