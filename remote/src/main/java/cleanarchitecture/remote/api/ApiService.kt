package cleanarchitecture.remote.api

import cleanarchitecture.remote.model.UserInfoResponseWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("getUserInfoTest")
    fun getUserInfo(@Path("identifier") userIdentifier: String):
            Observable<UserInfoResponseWrapper>
}