package com.example.wardrobe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new IntentIntegrator(MainActivity.this).initiateScan(IntentIntegrator.QR_CODE_TYPES);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ImageAdapter adapter = new ImageAdapter(this, R.layout.imageview, twitterImageList());
        ((GridView) findViewById(R.id.gridView)).setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null && scanResult.getContents() != null) {
            Toast.makeText(this, scanResult.getContents(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private List<Uri> twitterImageList() {
        File twitterDir = new File(Environment.getExternalStorageDirectory(), "/Pictures/Twitter/");
        List<File> files = Arrays.asList(twitterDir.listFiles());
        List<Uri> uris = new ArrayList<>();
        for (File file : files) {
            uris.add(Uri.fromFile(file));
        }
        return uris;
    }
}
