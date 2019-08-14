package com.theplanet.cleanarchitecturecourse.domain.usecases

import com.theplanet.cleanarchitecturecourse.domain.repository.SimpleRepository
import com.theplanet.cleanarchitecturecourse.domain.utils.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetUserInfoUseCaseTest {

    private lateinit var getUserInfoUseCase: GetUserInfoUseCase

    @Mock
    private lateinit var simpleRepository: SimpleRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getUserInfoUseCase = GetUserInfoUseCase(simpleRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }


    @Test
    fun test_getUserInfo_success() {
        val userInfoEntity = TestDataGenerator.generateUserInfo()

        Mockito.`when`(simpleRepository.getUserInfo(userInfoEntity.accountNumber))
            .thenReturn(Observable.just(userInfoEntity))

        val testObserver = getUserInfoUseCase.buildUseCase(userInfoEntity.accountNumber).test()

        Mockito.verify(simpleRepository, times(1)).getUserInfo(userInfoEntity.accountNumber)

        testObserver.assertSubscribed()
            .assertValue{
                it==userInfoEntity
            }
            .assertComplete()
    }



    @Test
    fun test_getUserInfo_error() {
        val userInfo = TestDataGenerator.generateUserInfo()
        val errorMsg = "ERROR OCCURRED"

        Mockito.`when`(simpleRepository.getUserInfo(userInfo.accountNumber))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val testObserver = getUserInfoUseCase.buildUseCase(userInfo.accountNumber).test()

        Mockito.verify(simpleRepository, times(1))
            .getUserInfo(userInfo.accountNumber)

        testObserver.assertSubscribed()
            .assertError { it.message?.equals(errorMsg) ?: false }
            .assertNotComplete()
    }

    @Test
    fun test_AccountUpgradeEligibility() {
        val upgradableUserInfo = TestDataGenerator.generateUpgradableUserInfo()
        assert(upgradableUserInfo.isEligibleForUpgrade)

        val userInfo = TestDataGenerator.generateUserInfo()
        assert(!userInfo.isEligibleForUpgrade)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_getUserInfoNoParameters_error() {
        val userInfo = TestDataGenerator.generateUserInfo()
        userInfo.accountNumber

        val testObserver = getUserInfoUseCase.buildUseCase().test()
        testObserver.assertSubscribed()
    }

}