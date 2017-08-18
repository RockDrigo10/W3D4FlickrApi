package com.example.admin.w3d4flickrapi;

import com.example.admin.w3d4flickrapi.model.Flickr;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 8/17/2017.
 */

public class RetroFitHelper {
    //public static final String BASE_URL = "http://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1";
    public static final String BASE_URL = "http://api.flickr.com/";
    public static final String PATH = "services/feeds/photos_public.gne";
    public static final String QUERY_TAG = "kitten";
    public static final String QUERY_FORMAT = "json";
    public static final String QUERY_NOJASONCALLBACK = "1";

    public static Retrofit create(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit =  new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Call<Flickr> getFlickr(){
        Retrofit retrofit = create();
        FlickrService flickrService = retrofit.create(FlickrService.class);
        return flickrService.getFlickrData(QUERY_TAG,QUERY_FORMAT,QUERY_NOJASONCALLBACK);
    }

    public interface FlickrService {

        @GET(PATH)
        Call<Flickr> getFlickrData(@Query("tag") String tag, @Query("format") String format, @Query("nojsoncallback") String nojson);
    }
}
