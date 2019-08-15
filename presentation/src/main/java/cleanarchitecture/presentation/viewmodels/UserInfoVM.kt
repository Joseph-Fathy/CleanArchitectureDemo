package cleanarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import cleanarchitecture.domain.entities.UserInfoDomainEntity
import cleanarchitecture.domain.usecases.GetUserInfoUseCase
import cleanarchitecture.presentation.data.mapper.Mapper
import cleanarchitecture.presentation.data.model.Resource
import cleanarchitecture.presentation.data.model.UserInfoPresentationModel
import cleanarchitecture.presentation.qualifier.UserIdentity
import cleanarchitecture.presentation.viewmodels.base.BaseVM
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
            .buildUseCase(userIdentifier)   //return Observable<UserInfoDomainEntity>
            .map {
                domainEntity-> userInfoMapper.to(domainEntity) // return presentation model
            }
            .map {
                presentationModel-> Resource.success(presentationModel) // wrap with UI status
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