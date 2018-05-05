package com.rzm.myrxjava;

import android.support.annotation.NonNull;

public abstract class  BasicFuseableObserver<T, U> implements Observer<T> {
    protected final Observer<? super U> actual;
    public BasicFuseableObserver(Observer<? super U> actual) {
        this.actual = actual;
    }

    @Override
    public void onSubscribe() {
        actual.onSubscribe();
    }


    @Override
    public void onError(@NonNull Throwable throwable) {
        actual.onError(throwable);
    }

    @Override
    public void onComplete() {
        actual.onComplete();
    }
}
