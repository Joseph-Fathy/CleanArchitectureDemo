package com.theplanet.cleanarchitecturecourse.util

import com.theplanet.cleanarchitecturecourse.model.UserInfoPresentationModel



class TestDataGenerator {

    companion object {
        fun generateUserInfo(): UserInfoPresentationModel {
            return UserInfoPresentationModel(
                "1BFC9A38E6C7",
                "John Doe",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "March-12, 2018",
                false,
                4579.75,
                "savings",
                4,
                false
            )
        }

        fun generateUpgradableUserInfo(): UserInfoPresentationModel {
            return UserInfoPresentationModel(
                "1BFC9A38E6C7",
                "Agent Smith",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "March-01, 2018",
                false,
                45579.75,
                "credit-card",
                11,
                true
            )
        }


    }
}