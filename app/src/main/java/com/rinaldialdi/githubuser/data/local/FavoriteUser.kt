package com.rinaldialdi.githubuser.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//Entity untuk merepresentasikan dari tabel pada database, dideklarasikan juga kolom" yang diperlukan
@Entity (tableName = "favorite_user")
data class FavoriteUser(
    val login       : String,
    @PrimaryKey
    val id          : Int,
    val avatar_url  : String
) : Serializable
