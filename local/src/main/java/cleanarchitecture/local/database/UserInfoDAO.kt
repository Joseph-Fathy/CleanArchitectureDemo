package cleanarchitecture.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cleanarchitecture.local.model.UserInfoLocal
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface UserInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserInfo(userInfo: UserInfoLocal)

    @Query("SELECT * FROM user_info order by _id desc limit 1")
    fun getUserInfo(): Observable<UserInfoLocal>

    @Query("DELETE FROM user_info")
    fun clearCachedUserInfo(): Completable
}