package cleanarchitecture.domain.usecases

import cleanarchitecture.domain.entities.UserInfoDomainEntity
import cleanarchitecture.domain.repository.DomainRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.drulabs.bankbuddy.domain.qualifiers.Background
import org.drulabs.bankbuddy.domain.qualifiers.Foreground
import org.drulabs.bankbuddy.domain.usecases.base.ObservableUseCase
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val domainRepository: DomainRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<UserInfoDomainEntity, String>(backgroundScheduler, foregroundScheduler) {

    override fun generateObservable(input: String?): Observable<UserInfoDomainEntity> {
        if (input == null)
            throw IllegalArgumentException("User identifier can't be null")

        //delegate the task of getting user info object to the repository
        return domainRepository.getUserInfo(input)
    }
}