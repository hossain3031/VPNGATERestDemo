package com.samsolution.vpngaterestdemo;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Base64;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String encodedToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)) //Here we are using the GsonConverterFactory to directly convert json data to object
                .baseUrl(Api.BASE_URL)
                .build();

        Api api = retrofit.create(Api.class);

        String tokenValue = getResources().getString(R.string.token);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            encodedToken = Base64.getEncoder().encodeToString(tokenValue.getBytes());
        }

        Call<ServerResponse> call = api.getServerResult(encodedToken);


        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onResponse: " + response.message());

                ServerResponse server = response.body();

                if (server != null){
                    for (Result result : server.getResults()){
                        Log.i("sever", result.getHostName());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
