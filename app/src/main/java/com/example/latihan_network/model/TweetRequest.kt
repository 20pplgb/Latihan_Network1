package com.example.latihan_network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TweetRequest {

    @SerializedName("teks")
    @Expose
    var teks : String? = null

    @SerializedName("author")
    @Expose
    var author : String? = null
}