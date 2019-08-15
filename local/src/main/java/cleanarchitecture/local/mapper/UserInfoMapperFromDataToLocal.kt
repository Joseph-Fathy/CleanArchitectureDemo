package cleanarchitecture.local.mapper

import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.local.model.UserInfoLocal
import javax.inject.Inject

/**convert format of the UserInfo from DATA format (UserInfoDataModel)
 * to Local format (UserInfoLocal) and ViseVersa*/


class UserInfoMapperFromDataToLocal @Inject constructor() : Mapper<UserInfoDataModel, UserInfoLocal> {

    //Local Layer to Data Layer
    override fun from(e: UserInfoLocal): UserInfoDataModel {
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


    //Data Layer to Local Layer
    override fun to(t: UserInfoDataModel): UserInfoLocal {
        return UserInfoLocal(
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