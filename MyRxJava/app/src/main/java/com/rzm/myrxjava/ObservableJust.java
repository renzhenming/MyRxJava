package com.rzm.myrxjava;

class ObservableJust<T> extends Observable<T>{
    private T t;

    public ObservableJust(T t) {
        this.t = t;
    }
    @Override
    protected void subscribeActual(Observer<T> observer) {
        //没有直接使用observer调用相应的方法，而是通过一个代理实现的
        //这样做有利于代码扩展
        ScalarDisposable sd = new ScalarDisposable(observer,t);
        observer.onSubscribe();
        sd.run();
    }

    public class ScalarDisposable<T> {
        private final T item;
        private Observer observer;

        public <T> ScalarDisposable(Observer<T> observer, T t) {
            this.observer = observer;
            this.item = t;
        }

        public void run() {
            try {
                observer.onNext(t);
                observer.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                observer.onError(e);
            }
        }
    }
}
