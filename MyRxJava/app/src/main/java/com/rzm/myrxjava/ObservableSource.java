package com.rzm.myrxjava;

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
