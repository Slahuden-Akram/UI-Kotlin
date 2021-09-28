package com.example.uiprojectkotlin

import com.example.uiprojectkotlin.DataModel
import android.telecom.Call
import retrofit2.http.GET

interface ApiInterface {
//    @GET("photos")
    @GET("cars_list.json")
//    @GET("/v1/gifs/search?q=all&api_key=dc6zaTOxFJmzC")
    fun getPhotos() : retrofit2.Call<List<DataModel>>
}