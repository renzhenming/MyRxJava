package com.rzm.myrxjava;

public abstract class Observable<T> implements ObservableSource<T> {
    public static<T> Observable<T> just(T t) {
        return onAssembly(new ObservableJust<T>(t));
    }

    public final <R> Observable<R> map(Function<? super T, ? extends R> mapper) {
        return onAssembly(new ObservableMap<T, R>(this, mapper));
    }

    public final ObservableSource<T> subscribeOn(Schedulers scheduler){
        return onAssembly(new ObservableSchedules<T>(this, scheduler));
    }

    private static <T> Observable<T> onAssembly(Observable<T> source) {
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);


}
