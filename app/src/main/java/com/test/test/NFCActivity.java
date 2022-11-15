package com.test.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NFCActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_hasNFC;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcactivity);
        bindViews();
        mContext = NFCActivity.this;
    }

    private void bindViews() {
        btn_hasNFC = (Button) findViewById(R.id.btn_hasNFC);

        btn_hasNFC.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_hasNFC:
                if (hasNFC(mContext)) {
                    Toast.makeText(mContext, "有NFC ", Toast.LENGTH_SHORT).show();
                }

            default:
                break;
        }
    }

    public static boolean hasNFC(Context context) {
        PackageManager packageManager = context.getPackageManager();

        return packageManager.hasSystemFeature(PackageManager.FEATURE_NFC);
    }
}