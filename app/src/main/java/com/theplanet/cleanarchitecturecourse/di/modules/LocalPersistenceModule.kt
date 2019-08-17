package com.theplanet.cleanarchitecturecourse.di.modules

import android.app.Application
import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.data.repository.LocalDataSource
import cleanarchitecture.local.database.AppDatabase
import cleanarchitecture.local.mapper.Mapper
import cleanarchitecture.local.mapper.UserInfoMapperFromDataToLocal
import cleanarchitecture.local.model.UserInfoLocal
import cleanarchitecture.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource

        @Binds
        fun bindUserInfoMapper(
            userInfoMapper: UserInfoMapperFromDataToLocal
        ): Mapper<UserInfoDataModel, UserInfoLocal>


    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = AppDatabase.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesUserInfoDAO(
        appDB: AppDatabase
    ) = appDB.getUserInfoDao()


}
