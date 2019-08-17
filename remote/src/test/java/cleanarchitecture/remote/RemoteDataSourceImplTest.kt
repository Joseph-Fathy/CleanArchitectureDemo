package cleanarchitecture.remote

import cleanarchitecture.data.repository.RemoteDataSource
import cleanarchitecture.remote.api.ApiService
import cleanarchitecture.remote.mapper.UserInfoMapperFromDataToRemote
import cleanarchitecture.remote.model.UserInfoResponseWrapper
import cleanarchitecture.remote.source.RemoteDataSourceImpl
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
class RemoteDataSourceImplTest {

    @Mock
    private lateinit var apiService: ApiService

    private val userInfoMapperFromDataToRemote = UserInfoMapperFromDataToRemote()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSourceImpl(
            userInfoMapperFromDataToRemote,
            apiService
        )
    }

    @Test
    fun test_getUserInfo_success() {
        val userInfoNetwork = NetworkTestDataGenerator.generateUserInfo()
        val statusOfNetworkResponse = NetworkTestDataGenerator.getNetworkStatusResponse()
        val userIdentifier = "AEZ19EDH2QZ"

        val mockResponse = UserInfoResponseWrapper(userInfoNetwork)
        mockResponse.statusOfNetworkResponse=statusOfNetworkResponse


        Mockito.`when`(apiService.getUserInfo())
            .thenReturn(Observable.just(mockResponse))

        remoteDataSource.getUserInfo()
            .test()
            .assertSubscribed()
            .assertValue {
                val data = userInfoMapperFromDataToRemote.to(it)
                data == userInfoNetwork
            }
            .assertComplete()

        Mockito.verify(apiService, times(1))
            .getUserInfo()
    }

    @Test
    fun test_getUserInfo_error() {
        val userIdentifier = "AEZ19EDH2QZ"
        val errorMsg = "ERROR"

        Mockito.`when`(apiService.getUserInfo())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        remoteDataSource.getUserInfo()
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }


}