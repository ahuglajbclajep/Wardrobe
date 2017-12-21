package com.example.wardrobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends CameraIntentActivity {
    public static final String IMAGE_URI = "com.example.wardrobe.IMAGE_URI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(StartActivity.QR_CODE));
        setSupportActionBar(toolbar);

        ((BottomNavigationView) findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(
                new BottomNavigationViewListener(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageAdapter adapter = new ImageAdapter(this, R.layout.imageview, Util.getImageList(this));
        ((GridView) findViewById(R.id.gridView)).setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BottomNavigationViewListener.REQUEST_IMAGE_CAPTURE:
                if (canReadImageUri()) {
                    startActivity(new Intent(this, EditActivity.class).putExtra(IMAGE_URI, imageUri));
                }
                break;
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
}
