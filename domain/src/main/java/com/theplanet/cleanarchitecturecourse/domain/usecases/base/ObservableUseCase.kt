package org.drulabs.bankbuddy.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Scheduler

/** T is the output type and Input is the input parameter type*/
abstract class ObservableUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    //sub classes will implement this method and provide the real observable
    protected abstract fun generateObservable(input: Input? = null): Observable<T>

    fun buildUseCase(input: Input? = null): Observable<T> {
        return generateObservable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}