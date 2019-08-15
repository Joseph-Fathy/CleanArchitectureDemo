package cleanarchitecture.remote.model

import cleanarchitecture.remote.model.base.BaseResponseWrapper
import com.google.gson.annotations.SerializedName

class UserInfoResponseWrapper(
    @SerializedName("userInfo") val userInfo: UserInfoNetwork
): BaseResponseWrapper()