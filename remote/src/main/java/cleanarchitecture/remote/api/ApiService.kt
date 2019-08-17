package cleanarchitecture.remote.api

import cleanarchitecture.remote.api.ApiEndPoints.USER_INFO
import cleanarchitecture.remote.model.UserInfoResponseWrapper
import io.reactivex.Observable
import retrofit2.http.POST


interface ApiService {

    @POST(USER_INFO)
    fun getUserInfo():Observable<UserInfoResponseWrapper>
}