package com.theplanet.cleanarchitecturecourse

import com.theplanet.cleanarchitecturecourse.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp: DaggerApplication() {
/*    override fun onCreate() {
        super.onCreate()
        AppComponent.init(this).inject(this)
    }*/

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}