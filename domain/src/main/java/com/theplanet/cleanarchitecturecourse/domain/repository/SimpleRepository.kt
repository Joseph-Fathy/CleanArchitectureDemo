package com.theplanet.cleanarchitecturecourse.domain.repository

import com.theplanet.cleanarchitecturecourse.domain.entities.UserInfoDomainEntity
import io.reactivex.Observable

/** This is the contract set by the domain layer for the data layer
 * It contains the signature of functions needed by the use cases to work*/
//You can add new repositories depending on the use cases.
interface SimpleRepository {
    fun getUserInfo(userId:String):Observable<UserInfoDomainEntity>
}