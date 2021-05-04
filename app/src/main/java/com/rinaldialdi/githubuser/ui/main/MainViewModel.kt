package com.rinaldialdi.githubuser.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rinaldialdi.githubuser.api.RetrofitClient
import com.rinaldialdi.githubuser.data.model.User
import com.rinaldialdi.githubuser.data.model.UserRespons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query : String){
        RetrofitClient.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<UserRespons>{
                override fun onResponse(call: Call<UserRespons>, response: Response<UserRespons>) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserRespons>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getSearchUsers() : LiveData<ArrayList<User>>{
        return listUsers
    }
}