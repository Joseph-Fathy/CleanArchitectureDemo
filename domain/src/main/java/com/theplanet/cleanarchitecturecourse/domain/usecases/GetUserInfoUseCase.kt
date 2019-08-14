package com.theplanet.cleanarchitecturecourse.domain.usecases

import com.theplanet.cleanarchitecturecourse.domain.entities.UserInfoEntity
import com.theplanet.cleanarchitecturecourse.domain.repository.SimpleRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.drulabs.bankbuddy.domain.qualifiers.Background
import org.drulabs.bankbuddy.domain.qualifiers.Foreground
import org.drulabs.bankbuddy.domain.usecases.base.ObservableUseCase
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val simpleRepository: SimpleRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<UserInfoEntity, String>(backgroundScheduler, foregroundScheduler) {

    override fun generateObservable(input: String?): Observable<UserInfoEntity> {
        if (input == null)
            throw IllegalArgumentException("User identifier can't be null")

        //delegate the task of getting user info object to the repository
        return simpleRepository.getUserInfo(input)
    }
}