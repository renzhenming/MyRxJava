package com.rzm.myrxjava;

public abstract class Observable<T> implements ObservableSource<T> {
    public static<T> ObservableSource<T> just(T t) {
        return onAssembly(new ObservableJust<T>(t));
    }

    private static <T> ObservableSource<T> onAssembly(ObservableJust<T> source) {
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);
}
