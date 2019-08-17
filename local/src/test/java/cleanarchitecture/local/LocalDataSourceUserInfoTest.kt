package cleanarchitecture.local

import cleanarchitecture.data.repository.LocalDataSource
import cleanarchitecture.local.database.UserInfoDAO
import cleanarchitecture.local.mapper.UserInfoMapperFromDataToLocal
import cleanarchitecture.local.source.LocalDataSourceImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LocalDataSourceUserInfoTest {

    private val userInfoMapper = UserInfoMapperFromDataToLocal()


    @Mock
    private lateinit var userInfoDao: UserInfoDAO


    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        localDataSource = LocalDataSourceImpl(
            userInfoMapper,
            userInfoDao
        )
    }

    @Test
    fun test_getUserInfo_success() {
        val userIdentifier = "1BFC9A38E6C7"
        val userInfoLocal = LocalTestDataGenerator.generateUserInfo()

        Mockito.`when`(userInfoDao.getUserInfo())
            .thenReturn(Observable.just(userInfoLocal))

        localDataSource.getUserInfo()
            .test()
            .assertSubscribed()
            .assertValue { it == userInfoMapper.from(userInfoLocal) }
    }

    @Test
    fun test_saveUserInfo_success() {
        val userInfoLocal = LocalTestDataGenerator.generateUserInfo()

        localDataSource.saveUserInfo(
            userInfoMapper.from(userInfoLocal)
        )

        Mockito.verify(userInfoDao, times(1))
            .addUserInfo(userInfoLocal)
    }
}
