package com.example.flowexample.features.posts.data.datasources.remote.response


import com.google.gson.annotations.SerializedName

class GetPostApiResponse : ArrayList<GetPostApiResponse.GetPostApiResponseItem>(){
    data class GetPostApiResponseItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    )
}