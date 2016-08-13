package com.example.rxjavaretrofitdemo.api;

import com.example.rxjavaretrofitdemo.bean.MovieInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lucky on 16-8-11.
 */
public interface DoubanAPI {
    @GET("v2/movie/us_box")
    Observable<MovieInfo> getUSMovie();
}
