package com.example.wardrobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends CameraIntentActivity {
    public static final String IMAGE_URI = "com.example.wardrobe.IMAGE_URI";

    private class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = createCameraIntent();
                    if (intent != null && intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, CameraIntentActivity.REQUEST_IMAGE_CAPTURE);
                    }
                    break;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(StartActivity.QR_CODE));
        setSupportActionBar(toolbar);

        ((BottomNavigationView) findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(
                new BottomNavigationViewListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((GridView) findViewById(R.id.gridView)).setAdapter(
                new ImageAdapter(this, R.layout.imageview, Util.getImageList(this)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CameraIntentActivity.REQUEST_IMAGE_CAPTURE:
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
