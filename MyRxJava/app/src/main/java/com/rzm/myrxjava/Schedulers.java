package com.rzm.myrxjava;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public abstract class Schedulers {

    static Schedulers IO;
    static {
        IO = new IOSchedulers();
    }

    public static Schedulers io(){
        return IO;
    }

    public abstract <T> void scheduleDirect(Runnable scheduleTask);

    private static class IOSchedulers extends Schedulers {

        private final ExecutorService service;

        public IOSchedulers(){
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r,"线程池");
                }
            });
        }

        @Override
        public <T> void scheduleDirect(Runnable scheduleTask) {
            service.submit(scheduleTask);
        }
    }
}
