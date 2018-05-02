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
        Observable.just("url").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "onNext");

            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.i(TAG, "onError");
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }
}
