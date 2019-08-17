package cleanarchitecture.data.repository

import cleanarchitecture.data.mapper.Mapper
import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.domain.entities.UserInfoDomainEntity
import cleanarchitecture.domain.repository.DomainRepository
import io.reactivex.Observable
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val userInfoMapper: Mapper<UserInfoDomainEntity, UserInfoDataModel>,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DomainRepository {

    //fetch the data from remote data source
    //update the local data source
    //concat the local and remote data source observables

    /*in case any error (connection or server issues ) return empty
    observable and data from local data source is used instead*/

    override fun getUserInfo(): Observable<UserInfoDomainEntity> {

        val userInfoObservable = localDataSource.getUserInfo() //return Observable<UserInfoDataModel>
            .map { dataModel ->
                userInfoMapper.from(dataModel)  // return userInfoDomainEntity
            }

        return remoteDataSource.getUserInfo() //Observable<UserInfoDataModel>
            .map { dataModel ->
                localDataSource.saveUserInfo(dataModel)
                userInfoMapper.from(dataModel) // return userInfoDomainEntity
            }
            .onErrorResumeNext(Observable.empty())
            .concatWith(userInfoObservable)
    }
}