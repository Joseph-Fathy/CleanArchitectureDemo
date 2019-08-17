package com.theplanet.cleanarchitecturecourse.di.modules

import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.data.repository.RemoteDataSource
import cleanarchitecture.remote.api.ApiService
import cleanarchitecture.remote.mapper.Mapper
import cleanarchitecture.remote.mapper.UserInfoMapperFromDataToRemote
import cleanarchitecture.remote.model.UserInfoNetwork
import cleanarchitecture.remote.source.RemoteDataSourceImpl
import com.theplanet.cleanarchitecturecourse.utils.Constants.BASE_URL
import com.theplanet.cleanarchitecturecourse.utils.Constants.CONNECTION_TIMEOUT_SECONDS
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {

    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataSource

        @Binds
        fun bindUserInfoMapper(
            userInfoMapper: UserInfoMapperFromDataToRemote
        ): Mapper<UserInfoDataModel, UserInfoNetwork>


    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    @Provides
    fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY


        val builder = OkHttpClient.Builder()
        builder
            .readTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return builder.build()
    }
}