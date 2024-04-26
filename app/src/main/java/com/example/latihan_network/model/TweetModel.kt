package com.example.latihan_network.model

data class TweetResponse(
    val success : Boolean,
    val message : String,
    val data : List<Tweet>
)

data class Tweet(
    val id : Int,
    val teks : String,
    val author : String
)

data class PostResponse(
    val success : Boolean,
    val message : String,
)