package com.theplanet.cleanarchitecturecourse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.theplanet.cleanarchitecturecourse.domain.entities.UserInfoDomainEntity
import com.theplanet.cleanarchitecturecourse.domain.usecases.GetUserInfoUseCase
import com.theplanet.cleanarchitecturecourse.mapper.Mapper
import com.theplanet.cleanarchitecturecourse.model.Resource
import com.theplanet.cleanarchitecturecourse.model.UserInfoPresentationModel
import com.theplanet.cleanarchitecturecourse.qualifiers.UserIdentity
import com.theplanet.cleanarchitecturecourse.viewmodel.base.BaseVM
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class UserInfoVM @Inject internal constructor(
    @UserIdentity private val userIdentifier: String,
    private val userInfoMapper: Mapper<UserInfoDomainEntity, UserInfoPresentationModel>,
    private val userInfoUseCase: GetUserInfoUseCase
) : BaseVM() {
    val userInfoResource: LiveData<Resource<UserInfoPresentationModel>>
        get() = userInfoUseCase
            .buildUseCase(userIdentifier)
            .map { userInfoMapper.to(it) }
            .map {
                Resource.success(it)
            }
            .startWith(Resource.loading())
            .onErrorResumeNext(
                Function
                {
                    Observable.just(Resource.error(it.localizedMessage))
                }
            ).toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
}