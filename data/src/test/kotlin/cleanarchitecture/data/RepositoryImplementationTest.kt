package cleanarchitecture.data

import cleanarchitecture.data.mapper.UserInfoMapperFromDomainToData
import cleanarchitecture.data.repository.DataRepositoryImpl
import cleanarchitecture.data.repository.LocalDataSource
import cleanarchitecture.data.repository.RemoteDataSource
import cleanarchitecture.domain.repository.DomainRepository
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
class RepositoryImplementationTest {

    private lateinit var domainRepository: DomainRepository

    private val userInfoMapperFromDomainToData = UserInfoMapperFromDomainToData()

    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        domainRepository = DataRepositoryImpl(
            userInfoMapperFromDomainToData,
            localDataSource,
            remoteDataSource
        )
    }

    @Test
    fun test_getUserInfo_local_remote_interaction() {
        val userInfoData = DataTestDataGenerator.generateUserInfo()
        val userInfoDomain = userInfoMapperFromDomainToData.from(userInfoData)

        Mockito.`when`(remoteDataSource.getUserInfo())
            .thenReturn(Observable.just(userInfoData))
        Mockito.`when`(localDataSource.getUserInfo())
            .thenReturn(Observable.just(userInfoData))

        val testSubscriber = domainRepository.getUserInfo().test()

        testSubscriber.assertSubscribed()
            .assertValueCount(2)
            .assertValues(userInfoDomain, userInfoDomain)
            .assertComplete()

        Mockito.verify(localDataSource, times(1))
            .saveUserInfo(userInfoData)

        Mockito.verify(remoteDataSource, times(1))
            .getUserInfo()
    }

    @Test
    fun test_getUserInfo_remote_error() {
        val userIdentifier = "1BFC9A38E6C7"
        val userInfoData = DataTestDataGenerator.generateUserInfo()
        val userInfoDomain = userInfoMapperFromDomainToData.from(userInfoData)

        Mockito.`when`(remoteDataSource.getUserInfo())
            .thenReturn(Observable.error(Throwable()))
        Mockito.`when`(localDataSource.getUserInfo())
            .thenReturn(Observable.just(userInfoData))

        val testSubscriber = domainRepository.getUserInfo().test()

        testSubscriber.assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                it == userInfoDomain
            }
            .assertComplete()

        Mockito.verify(localDataSource, times(1))
            .getUserInfo()
    }

}