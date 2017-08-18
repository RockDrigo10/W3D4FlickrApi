package com.example.admin.w3d4flickrapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.w3d4flickrapi.model.Flickr;
import com.example.admin.w3d4flickrapi.model.Item;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1";
    public static final String TAG = "MainActivity";
    RecyclerView rvImages;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Flickr Api");
        rvImages = (RecyclerView) findViewById(R.id.rvImages);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        itemAnimator = new DefaultItemAnimator();
        rvImages.setLayoutManager(layoutManager);
        rvImages.setItemAnimator(itemAnimator);
        final retrofit2.Call<Flickr> flickrCall = RetroFitHelper.getFlickr();
        flickrCall.enqueue(new retrofit2.Callback<Flickr>() {
            @Override
            public void onResponse(Call<Flickr> call, Response<Flickr> response) {
                ArrayList<Item> items = (ArrayList<Item>) response.body().getItems();
                FlirckrAdapter flirckrAdapter = new FlirckrAdapter(items);
                rvImages.setAdapter(flirckrAdapter);
                flirckrAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Flickr> call, Throwable t) {

            }
        });

    }
}
