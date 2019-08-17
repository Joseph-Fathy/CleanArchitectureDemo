package com.theplanet.cleanarchitecturecourse.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cleanarchitecture.domain.entities.UserInfoDomainEntity
import cleanarchitecture.presentation.factory.ViewModelFactory
import cleanarchitecture.presentation.mapper.Mapper
import cleanarchitecture.presentation.mapper.UserInfoMapperFromDomainToPresentation
import cleanarchitecture.presentation.model.UserInfoPresentationModel
import cleanarchitecture.presentation.viewmodels.UserInfoVM
import com.theplanet.cleanarchitecturecourse.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserInfoVM::class)
    abstract fun bindsUserInfoViewModel(userInfoVM: UserInfoVM): ViewModel


    @Binds
    abstract fun bindsUserInfoMapper(
        userInfoEntityMapper: UserInfoMapperFromDomainToPresentation
    ): Mapper<UserInfoDomainEntity, UserInfoPresentationModel>


}