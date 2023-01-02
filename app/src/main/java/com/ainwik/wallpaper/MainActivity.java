package com.ainwik.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ImageModel>modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView nature, car,bus,train, trending;
    ImageButton search;
EditText e;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        nature=findViewById(R.id.nature);
        car=findViewById(R.id.car);
        train=findViewById(R.id.train);
        bus=findViewById(R.id.bus);
        trending=findViewById(R.id.trending);
        search=findViewById(R.id.buttonsearch);
        e=findViewById(R.id.editSearch);

        recyclerView=findViewById(R.id.recycler);

        modelClassList=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findphotos();

        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getSearchImage(query);
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getSearchImage(query);
            }
        });
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getSearchImage(query);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="bus";
                getSearchImage(query);
            }
        });
        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphotos();

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=e.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                }
                else{
                    getSearchImage(query);
                }

            }
        });

    }

    private void getSearchImage(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,15,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }

    private void findphotos() {
        ApiUtilities.getApiInterface().getImage(15,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }
}