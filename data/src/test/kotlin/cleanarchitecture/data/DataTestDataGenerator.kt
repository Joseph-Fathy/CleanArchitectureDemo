package cleanarchitecture.data

import cleanarchitecture.data.model.UserInfoDataModel


class DataTestDataGenerator {

    companion object {
        fun generateUserInfo(): UserInfoDataModel {
            return UserInfoDataModel(
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
    }
}