package cleanarchitecture.remote.model.base

import com.google.gson.annotations.SerializedName

open class BaseResponseWrapper{

    @SerializedName("status")
    var statusOfNetworkResponse: StatusOfNetworkResponse?=null

    fun getStatusCode(): Int = statusOfNetworkResponse?.code ?: -1
}