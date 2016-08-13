package com.example.rxjavaretrofitdemo.util;

import com.example.rxjavaretrofitdemo.api.DoubanAPI;
import com.example.rxjavaretrofitdemo.bean.MovieInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lucky on 16-8-11.
 */
public class RetrofitUtil {
    private Retrofit retrofit;
    private DoubanAPI doubanService;
    private static RetrofitUtil instance;
    private RetrofitUtil() {
        retrofit=new Retrofit.Builder().baseUrl("https://api.douban.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build())
                            .build();
        doubanService=retrofit.create(DoubanAPI.class);
    }
    //创建单例模式
    public static RetrofitUtil getInstance(){
        if (instance == null) {
            synchronized (RetrofitUtil.class){
                if (instance == null) {
                    instance=new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    public void getUSMovie (Subscriber<MovieInfo> subscriber){
        doubanService.getUSMovie().subscribeOn(Schedulers.io())
                                  .unsubscribeOn(Schedulers.io())
                                  .observeOn(AndroidSchedulers.mainThread())
                                  .subscribe(subscriber);

    }
}
