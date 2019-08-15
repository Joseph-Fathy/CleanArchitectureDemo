package cleanarchitecture.data.repository

import cleanarchitecture.data.model.UserInfoDataModel
import io.reactivex.Observable


interface RemoteDataSource {
    fun getUserInfo(identifier: String): Observable<UserInfoDataModel>
}