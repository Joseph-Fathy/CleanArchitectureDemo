package cleanarchitecture.remote.source

import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.data.repository.RemoteDataSource
import cleanarchitecture.remote.api.ApiService
import cleanarchitecture.remote.mapper.Mapper
import cleanarchitecture.remote.model.UserInfoNetwork
import io.reactivex.Observable

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val userInfoMapper: Mapper<UserInfoDataModel, UserInfoNetwork>,
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getUserInfo(): Observable<UserInfoDataModel> {
        return apiService.getUserInfo()
            .map { response ->
                userInfoMapper.from(response.userInfo)
            }
    }

}