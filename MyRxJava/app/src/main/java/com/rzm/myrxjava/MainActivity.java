package com.rzm.myrxjava;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "thread:"+Thread.currentThread().getName());
        Observable.just("我是网络图片url").map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                Log.i(TAG, "apply1 thread:"+Thread.currentThread().getName());
                Log.i(TAG, "apply1");
                s = s +" 加上一个时间戳后";
                return s;
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                Log.i(TAG, "apply2 thread:"+Thread.currentThread().getName());
                Log.i(TAG, "apply2");
                s = s +" 加上第二个参数后";
                return s;
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i(TAG, "onSubscribe thread:"+Thread.currentThread().getName());
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "onNext thread:"+Thread.currentThread().getName());
                Log.i(TAG, "onNext:"+s+" 开启下载这个图片");

            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.i(TAG, "onError");
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete thread:"+Thread.currentThread().getName());
                Log.i(TAG, "onComplete：下载完成");
            }
        });
    }
}
