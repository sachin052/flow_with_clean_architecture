package com.example.flowexample.features.posts.data.mapper

import com.example.flowexample.features.posts.data.datasources.remote.response.GetPostApiResponse
import com.example.flowexample.features.posts.domain.entity.PostEntity

fun GetPostApiResponse.toPostsEntities(): List<PostEntity> {
    return this.map { currentObject ->
        PostEntity(
            userID = currentObject.userId.toString(),
            postTitle = currentObject.title,
            postBody = currentObject.body)
    }}
