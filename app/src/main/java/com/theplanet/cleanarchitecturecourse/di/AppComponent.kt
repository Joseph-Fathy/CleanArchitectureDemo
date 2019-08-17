package com.theplanet.cleanarchitecturecourse.di

import android.app.Application
import com.theplanet.cleanarchitecturecourse.MyApp
import com.theplanet.cleanarchitecturecourse.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DomainModule::class,
        DataModule::class,
        LocalPersistenceModule::class,
        RemoteModule::class,
        PresentationModule::class,
        RxJavaModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    /**
     * A builder for a component.
     * Components may have a single nested static abstract class or interface annotated with @Component.Builder.
     * If they do, then the component's generated builder will match the API in the type.*/
    @Component.Builder
    interface Builder {
        fun build(): AppComponent


        /**Marks a method on a component builder or subcomponent builder that allows an instance
         * to be bound to some type within the component.*/
        @BindsInstance
        fun application(app: Application): Builder
    }


    /*companion object {
        lateinit var component: AppComponent

        fun init(app: Application): AppComponent {
            component = DaggerAppComponent.builder()
                .application(app)
                .build()
            return component
        }

        fun get(): AppComponent {
            if (this::component.isInitialized)
                return component
            else
                throw UninitializedPropertyAccessException("You cannot call get() appComponent as it's not initialized")
        }
    }*/


    override fun inject(target: MyApp)
}