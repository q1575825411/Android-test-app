package com.test.test.camera;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.test.test.R;
import com.test.test.databinding.ActivityPictureBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author sm2886
 */
public class PictureActivity extends AppCompatActivity {

    private ActivityPictureBinding binding;
    String num = "1";
    String size = "false";
    String url = "https://api.nyan.xyz/httpapi/sexphoto/?num=" + num + "&r18=" + size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPictureBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.actionButton.setOnClickListener(view -> getPictureFormNet(url));
    }


    private void getPictureFormNet(String url) {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("onFailure", "请求 {" + call.request().url() + "} 出现异常 { " + e.getMessage() + "}");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                String body = Objects.requireNonNull(response.body()).toString();
                Log.i("onResponse", "请求 {" + call.request().url() + "} 的响应结果为 {" + body + "}");
                runOnUiThread(() -> binding.actionImage.setImageResource(R.drawable.siez));
            }
        });


    }
}