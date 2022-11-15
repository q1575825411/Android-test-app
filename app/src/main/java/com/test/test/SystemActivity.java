package com.test.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sm2886
 */
public class SystemActivity extends AppCompatActivity {

    private static final String TAG = "SystemActivity";
    public int mVar = 11;
    private int mWhat = 0;
    Spinner spinner;
    ToggleButton toggleButton2;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch mSwitch;
    Looper mLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        initView();
        Log.d(TAG, "hello");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * 当item被选择后调用此方法
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
            }

            /**
             * 只有当patent中的资源没有时，调用此方法
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Toast.makeText(getApplicationContext(), "开启", Toast.LENGTH_SHORT).show();
                spinner.setEnabled(false);
                toggleButton2.setEnabled(false);
            } else {
                Toast.makeText(getApplicationContext(), "关闭", Toast.LENGTH_SHORT).show();
                spinner.setEnabled(true);
                toggleButton2.setEnabled(true);
                spinner.setSelection(0);
            }
        });
        toggleButton2.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Toast.makeText(getApplicationContext(), "开启", Toast.LENGTH_SHORT).show();
                spinner.setEnabled(false);
                mSwitch.setEnabled(false);
            } else {
                Toast.makeText(getApplicationContext(), "关闭", Toast.LENGTH_SHORT).show();
                spinner.setEnabled(true);
                mSwitch.setEnabled(true);
                spinner.setSelection(0);
            }
        });
        mLooper = Looper.myLooper();
        // 线程池的使用
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            MessageHandler handler = new MessageHandler(mLooper, SystemActivity.this);
            Message message = Message.obtain();
            message.obj = "子线程的消息";
            mWhat = initData();
            message.what = mWhat;
            Log.d(TAG, "MessageHandler");
            handler.sendMessage(message);
        });
    }

    public void initView() {
        spinner = findViewById(R.id.spinner);
        toggleButton2 = findViewById(R.id.toggleButton2);
        mSwitch = findViewById(R.id.switchButton);
    }

    public int initData() {
        java.util.Random random = new java.util.Random();
        int result = random.nextInt(10);
        return result + 1;
    }

    static class MessageHandler extends Handler {
        WeakReference<SystemActivity> mActivity;

        public MessageHandler(Looper looper, SystemActivity activity) {
            super(looper);
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            SystemActivity activity = mActivity.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case 0:
                    activity.mVar = 1;
                    Log.d(TAG, "--msg-- obj:" + msg.obj.toString() + " what:" + msg.what + " val:" + activity.mVar);
                    break;
                case 1:
                    activity.mVar = 0;
                    Log.d(TAG, "--msg-- obj:" + msg.obj.toString() + " what:" + msg.what + " val:" + activity.mVar);
                    break;
                default:
                    Log.d(TAG, "--msg-- obj:" + msg.obj.toString() + " what:" + msg.what + " val:" + activity.mVar);
                    break;
            }
        }
    }
}