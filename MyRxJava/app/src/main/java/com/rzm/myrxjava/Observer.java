package com.rzm.myrxjava;

import android.support.annotation.NonNull;

public interface Observer<T> {
     void onSubscribe();
     void onNext(T t);
     void onError(@NonNull Throwable throwable);
     void onComplete();
}
