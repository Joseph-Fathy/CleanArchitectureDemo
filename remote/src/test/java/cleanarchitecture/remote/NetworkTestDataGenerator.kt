package cleanarchitecture.remote

import cleanarchitecture.remote.model.UserInfoNetwork
import cleanarchitecture.remote.model.base.StatusOfNetworkResponse

class NetworkTestDataGenerator {

    companion object {
        fun generateUserInfo(): UserInfoNetwork {
            return UserInfoNetwork(
                "1BFC9A38E6C7",
                "John Doe",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "March-12, 2018",
                false,
                4579.75,
                "savings",
                4
            )
        }

        fun getNetworkStatusResponse():StatusOfNetworkResponse{
            return StatusOfNetworkResponse(code = 0,details = "")
        }

    }
}