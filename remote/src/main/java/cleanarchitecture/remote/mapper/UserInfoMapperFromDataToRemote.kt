package cleanarchitecture.remote.mapper

import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.remote.model.UserInfoNetwork
import javax.inject.Inject

/**convert format of the UserInfo from DATA format (UserInfoDataModel)
 * to Remote format (UserInfoNetwork) and ViseVersa*/


class UserInfoMapperFromDataToRemote @Inject constructor() : Mapper<UserInfoDataModel, UserInfoNetwork> {

    //Network Layer to Data Layer
    override fun from(e: UserInfoNetwork): UserInfoDataModel {
        return UserInfoDataModel(
            accountNumber = e.accountNumber,
            displayName = e.displayName,
            address = e.address,
            displayableJoinDate = e.displayableJoinDate,
            premiumCustomer = e.premiumCustomer,
            accountBalance = e.accountBalance,
            accountType = e.accountType,
            unbilledTransactionCount = e.unbilledTransactionCount
        )
    }


    //Data Layer to Network Layer
    override fun to(t: UserInfoDataModel): UserInfoNetwork {
        return UserInfoNetwork(
            accountNumber = t.accountNumber,
            displayName = t.displayName,
            address = t.address,
            displayableJoinDate = t.displayableJoinDate,
            premiumCustomer = t.premiumCustomer,
            accountBalance = t.accountBalance,
            accountType = t.accountType,
            unbilledTransactionCount = t.unbilledTransactionCount
        )
    }
}