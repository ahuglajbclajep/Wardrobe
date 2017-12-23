package com.example.wardrobe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class BottomNavigationViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    private final MainActivity activity;

    BottomNavigationViewListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Intent intent = activity.createCameraIntent();
                if (intent != null && intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivityForResult(intent, CameraIntentActivity.REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.navigation_dashboard:
                activity.startActivity(new Intent(activity, StartActivity.class));
                break;
        }
        return true;
    }
}
