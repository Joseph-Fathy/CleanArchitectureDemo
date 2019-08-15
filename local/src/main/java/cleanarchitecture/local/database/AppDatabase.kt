package cleanarchitecture.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cleanarchitecture.local.model.UserInfoLocal

@Database(
    entities = [UserInfoLocal::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase:RoomDatabase()
{
    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "app_database.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(@NonNull context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }



    abstract fun getUserInfoDao(): UserInfoDAO
}