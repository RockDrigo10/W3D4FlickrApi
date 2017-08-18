package com.example.admin.w3d4flickrapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    ImageView ivFullImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ivFullImage = (ImageView) findViewById(R.id.ivFullImage);
        Intent intent = getIntent();
        String img =  intent.getStringExtra("image");
        Glide.with(this).load(img).into(ivFullImage);
    }
}
