package com.example.wardrobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends CameraIntentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
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
                Toast.makeText(this, imageUri.toString(), Toast.LENGTH_SHORT).show();
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
