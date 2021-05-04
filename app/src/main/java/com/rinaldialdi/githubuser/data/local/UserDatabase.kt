package com.rinaldialdi.githubuser.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    companion object{
        var INSTANCE : UserDatabase?= null


        fun getDatabase(context: Context): UserDatabase?{
            if (INSTANCE==null){
                kotlin.synchronized(UserDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user data").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favoriteUserDao(): FavoriteUserDao
}