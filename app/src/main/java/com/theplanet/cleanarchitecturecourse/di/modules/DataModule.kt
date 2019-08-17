package com.theplanet.cleanarchitecturecourse.di.modules

import cleanarchitecture.data.mapper.Mapper
import cleanarchitecture.data.mapper.UserInfoMapperFromDomainToData
import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.data.repository.DataRepositoryImpl
import cleanarchitecture.domain.entities.UserInfoDomainEntity
import cleanarchitecture.domain.repository.DomainRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: DataRepositoryImpl
    ): DomainRepository

    @Binds
    abstract fun bindsUserMapper(
        mapper: UserInfoMapperFromDomainToData
    ): Mapper<UserInfoDomainEntity, UserInfoDataModel>


}