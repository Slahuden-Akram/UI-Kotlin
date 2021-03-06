package com.example.uiprojectkotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataModel (
    @SerializedName("id")
    @Expose
    val id : Integer,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("desc")
    @Expose
    val desc: String,

    @SerializedName("image")
    @Expose val image: String
)