package com.rinaldialdi.githubuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteUserDao {

    @Insert
    suspend fun addToFavorite(favoriteUser: FavoriteUser)

    //fungsi untuk menampilkan favorite user
    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>

    //fungsi untuk memilih jika user akan difavoritkan
    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id= :id")
    suspend fun checkUser(id : Int) : Int
    //fungsi untuk menghapus user yang dipilih favorite
    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeFromFavorite(id: Int): Int

}