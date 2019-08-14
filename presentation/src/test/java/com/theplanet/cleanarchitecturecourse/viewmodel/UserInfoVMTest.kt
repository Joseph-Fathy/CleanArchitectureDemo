package com.theplanet.cleanarchitecturecourse.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.theplanet.cleanarchitecturecourse.domain.repository.SimpleRepository
import com.theplanet.cleanarchitecturecourse.domain.usecases.GetUserInfoUseCase
import com.theplanet.cleanarchitecturecourse.mapper.UserInfoEntityMapper
import com.theplanet.cleanarchitecturecourse.model.Status
import com.theplanet.cleanarchitecturecourse.util.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserInfoVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: SimpleRepository

    private lateinit var userInfoVM: UserInfoVM
    private val userInfoMapper = UserInfoEntityMapper()

    private val userInfo = TestDataGenerator.generateUserInfo()
    private val userInfoEntity = userInfoMapper.from(userInfo)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val getUserInfoTask = GetUserInfoUseCase(
            repository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        userInfoVM = UserInfoVM(
            userInfo.accountNumber,
            userInfoMapper,
            getUserInfoTask
        )
    }

    @Test
    fun test_getUserInfo_success() {

        Mockito.`when`(repository.getUserInfo(anyString()))
            .thenReturn(Observable.just(userInfoEntity))

        val userInfoResource = userInfoVM.userInfoResource

        userInfoResource.observeForever { /*Do nothing*/ }

        assertTrue(
            userInfoResource.value?.status == Status.SUCCESS
                    && userInfoResource.value?.data == userInfo
        )
    }

    @Test
    fun test_getUserInfo_error() {
        val errorMsg = "user info error in fetching data"
        Mockito.`when`(repository.getUserInfo(anyString()))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val userInfoResource = userInfoVM.userInfoResource

        userInfoResource.observeForever { /*Do nothing*/ }

        assertTrue(
            userInfoResource.value?.status == Status.ERROR
                    && userInfoResource.value?.message == errorMsg
        )
    }

}