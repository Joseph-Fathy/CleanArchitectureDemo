package cleanarchitecture.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import cleanarchitecture.local.database.AppDatabase
import cleanarchitecture.local.database.UserInfoDAO
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UserInfoDAOTest {

    private lateinit var appDB: AppDatabase
    private lateinit var userInfoDAO: UserInfoDAO

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDB = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        userInfoDAO = appDB.getUserInfoDao()
    }

    @After
    fun tearDown() {
        appDB.close()
    }

    @Test
    fun test_saveAndRetrieveUserInfo() {
        val userInfo = RoomTestDataGenerator.generateUserInfo()

        userInfoDAO.addUserInfo(userInfo)

        userInfoDAO.getUserInfo()
            .test()
            .assertValue {
                it == userInfo
            }.assertNotComplete() // As Room Observables are kept alive
    }

    @Test
    fun test_clearCachedUserInfo() {
        val userInfo = RoomTestDataGenerator.generateUserInfo()

        userInfoDAO.addUserInfo(userInfo)

        userInfoDAO.getUserInfo()
            .test()
            .assertValueCount(1)
            .assertValue {
                it == userInfo
            }

        userInfoDAO.clearCachedUserInfo().subscribe()

        userInfoDAO.getUserInfo()
            .test()
            .assertNoValues()
            .assertNotComplete()
    }

}