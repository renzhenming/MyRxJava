package com.rzm.myrxjava;

public interface HasUpstreamObservableSource<T> {
    /**
     * Returns the upstream source of this Observable.
     * <p>Allows discovering the chain of observables.
     * @return the source ObservableSource
     */
    ObservableSource<T> source();
}