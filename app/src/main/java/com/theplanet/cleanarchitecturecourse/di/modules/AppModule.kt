package com.theplanet.cleanarchitecturecourse.di.modules

import android.app.Application
import android.content.Context
import com.theplanet.cleanarchitecturecourse.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

}