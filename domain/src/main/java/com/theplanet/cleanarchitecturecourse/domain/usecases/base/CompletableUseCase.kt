package org.drulabs.bankbuddy.domain.usecases.base

import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class CompletableUseCase<in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    //sub classes will implement this method and provide the real observable
    protected abstract fun generateCompletable(input: Input? = null): Completable

    fun buildUseCase(input: Input? = null): Completable {
        return generateCompletable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}