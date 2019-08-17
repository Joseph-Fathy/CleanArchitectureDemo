package cleanarchitecture.local.source

import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.data.repository.LocalDataSource
import cleanarchitecture.local.database.UserInfoDAO
import cleanarchitecture.local.mapper.UserInfoMapperFromDataToLocal
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val userInfoMapper: UserInfoMapperFromDataToLocal,
    private val userInfoDAO: UserInfoDAO
) : LocalDataSource {
    override fun getUserInfo(): Observable<UserInfoDataModel> {
        return userInfoDAO.getUserInfo()
            .map { localModel ->
                userInfoMapper.from(localModel)
            }
    }

    override fun saveUserInfo(userInfoDataModel: UserInfoDataModel) {
        userInfoDAO.addUserInfo(userInfoMapper.to(userInfoDataModel))
    }
}