package cleanarchitecture.presentation.data.model

data class UserInfoPresentationModel (
    val accountNumber: String,
    val displayName: String,
    val address: String,
    val displayableJoinDate: String,
    val premiumCustomer: Boolean,
    val accountBalance: Double,
    val accountType: String,
    val unbilledTransactionCount: Int,
    val isEligibleForUpgrade: Boolean
)