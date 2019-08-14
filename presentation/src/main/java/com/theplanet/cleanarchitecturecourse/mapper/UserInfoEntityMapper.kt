package com.theplanet.cleanarchitecturecourse.mapper


import com.theplanet.cleanarchitecturecourse.domain.entities.UserInfoDomainEntity
import com.theplanet.cleanarchitecturecourse.model.UserInfoPresentationModel
import javax.inject.Inject

/**convert format of the UserInfo from DOMAIN format (UserInfoDomainEntity)
 * to PRESENTATION format (UserInfoPresentationModel) and ViseVersa*/


class UserInfoEntityMapper @Inject constructor() : Mapper<UserInfoDomainEntity, UserInfoPresentationModel> {
    override fun from(e: UserInfoPresentationModel): UserInfoDomainEntity {
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

    override fun to(t: UserInfoDomainEntity): UserInfoPresentationModel {
        return UserInfoPresentationModel(
            accountNumber = t.accountNumber,
            displayName = t.displayName,
            address = t.address,
            displayableJoinDate = t.displayableJoinDate,
            premiumCustomer = t.premiumCustomer,
            accountBalance = t.accountBalance,
            accountType = t.accountType,
            unbilledTransactionCount = t.unbilledTransactionCount,
            isEligibleForUpgrade = t.isEligibleForUpgrade
        )
    }
}