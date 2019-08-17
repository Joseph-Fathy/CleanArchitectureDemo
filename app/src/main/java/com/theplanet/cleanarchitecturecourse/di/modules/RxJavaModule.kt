package com.theplanet.cleanarchitecturecourse.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RxJavaModule {

    @Provides
    fun  compositeDisposable() = CompositeDisposable()
}