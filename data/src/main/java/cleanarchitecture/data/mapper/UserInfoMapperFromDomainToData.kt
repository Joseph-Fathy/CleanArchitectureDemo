package cleanarchitecture.data.mapper


import cleanarchitecture.data.model.UserInfoDataModel
import cleanarchitecture.domain.entities.UserInfoDomainEntity
import javax.inject.Inject

/**convert format of the UserInfo from DOMAIN format (UserInfoDomainEntity)
 * to DATA format (UserInfoPresentationModel) and ViseVersa*/


class UserInfoMapperFromDomainToData @Inject constructor() : Mapper<UserInfoDomainEntity, UserInfoDataModel> {

    //Presentation Layer to Domain Layer
    override fun from(e: UserInfoDataModel): UserInfoDomainEntity {
        return UserInfoDomainEntity(
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


    //Domain Layer to Presentation Layer
    override fun to(t: UserInfoDomainEntity): UserInfoDataModel {
        return UserInfoDataModel(
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