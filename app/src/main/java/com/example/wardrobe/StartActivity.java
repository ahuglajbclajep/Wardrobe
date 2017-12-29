package com.example.wardrobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class StartActivity extends Activity {
    public static final String QR_CODE = "com.example.wardrobe.QR_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new IntentIntegrator(this).initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null && scanResult.getContents() != null) {
            startActivity(new Intent(this, MainActivity.class)
                    .putExtra(QR_CODE, scanResult.getContents()).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        finish();
    }
}
