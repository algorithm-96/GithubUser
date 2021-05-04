package com.rinaldialdi.githubuser.api

import com.rinaldialdi.githubuser.data.model.DetailUserResponse
import com.rinaldialdi.githubuser.data.model.User
import com.rinaldialdi.githubuser.data.model.UserRespons
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query ("q") query : String

    ): Call<UserRespons>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
            @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
            @Path("username") username : String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
            @Path("username") username : String
    ): Call<ArrayList<User>>
}