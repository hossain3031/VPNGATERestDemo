package com.samsolution.vpngaterestdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String encodedToken = "QklseUVlTHRYTTdEUThYTTdEUThiZVA1Unh5RWVMdHdBQUFBRXdBQUFBRUdiZVA1UnhHQkls";
    ListView listView;
    ServerResponse server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)) //Here we are using the GsonConverterFactory to directly convert json data to object
                .baseUrl(Api.BASE_URL)
                .build();

        List<String> list;

        Api api = retrofit.create(Api.class);

        Call<ServerResponse> call = api.getServerResult(encodedToken);

        final List<String> finalList = new ArrayList<>();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onResponse: " + response.message());

                server = response.body();


                Toast.makeText(MainActivity.this, " ", Toast.LENGTH_SHORT).show();

                if (server != null) {
                    for (Result result : server.getResults()) {
                        Log.i("server", result.getHostName());
                        Log.i("server", result.getIP());
                        Log.i("server", result.getScore());
                        Log.i("server", result.getLatency());
                        Log.i("server", result.getCountryLong());
                        Log.i("server", result.getCountryShort());

                        finalList.add(result.getCountryLong());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.activity_list_item, finalList);
        listView.setAdapter(adapter);

    }
}
