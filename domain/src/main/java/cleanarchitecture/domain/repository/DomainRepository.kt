package cleanarchitecture.domain.repository

import cleanarchitecture.domain.entities.UserInfoDomainEntity
import io.reactivex.Observable

/** This is the contract set by the domain layer for the data layer
 * It contains the signature of functions needed by the use cases to work*/
//You can add new repositories depending on the use cases.
interface DomainRepository {
    fun getUserInfo(userId:String):Observable<UserInfoDomainEntity>
}