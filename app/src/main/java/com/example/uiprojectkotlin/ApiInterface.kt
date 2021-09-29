package com.example.uiprojectkotlin

import com.example.uiprojectkotlin.DataModel
import android.telecom.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("0bOaTu")
    fun getPhotos() : retrofit2.Call<List<DataModel>>

}