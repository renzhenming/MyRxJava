package com.rzm.myrxjava;

class ObservableSchedules<T> extends AbstractObservableWithUpstream<T, T>  {
    private final Schedulers schedulers;

    public ObservableSchedules(Observable<T> observable, Schedulers scheduler) {
        super(observable);
        this.schedulers = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        schedulers.scheduleDirect(new ScheduleTask(source,observer));
    }

    class ScheduleTask implements Runnable{
        private final Observer<T> observer;
        private final ObservableSource<T> source;

        public ScheduleTask(ObservableSource<T> source, Observer<T> observer) {
            this.source = source;
            this.observer = observer;
        }

        @Override
        public void run() {
            //线程池最终会执行这个方法，这个方法执行会调用上一级的subscribe方法
            //逐级向上传递，然后又逐级向下执行onNext等，这个run方法执行在子线程
            source.subscribe(observer);
        }
    }
}
