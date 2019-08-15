package cleanarchitecture.remote.model.base

import com.google.gson.annotations.SerializedName

data class StatusOfNetworkResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("details") val details: String=""
)