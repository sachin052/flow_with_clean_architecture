package com.example.flowexample.features.posts.data.repo_impl

import com.example.flowexample.core.failure.Failure
import com.example.flowexample.core.helpers.Either
import com.example.flowexample.core.helpers.map
import com.example.flowexample.core.helpers.safeFlowBuilder
import com.example.flowexample.features.posts.data.datasources.remote.response.GetPostApiResponse
import com.example.flowexample.features.posts.data.datasources.remote.services.PostsApiService
import com.example.flowexample.features.posts.data.mapper.toPostsEntities
import com.example.flowexample.features.posts.domain.repo.PostRepo
import com.example.flowexample.features.posts.domain.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepoImpl @Inject constructor(private val postsApiService: PostsApiService) : PostRepo {

    override suspend fun getAllPosts(): Flow<Either<Failure, List<PostEntity>>> =
          safeFlowBuilder { postsApiService.getAllPosts() }.map { value ->
            when (value) {
                is Either.Left -> value
                is Either.Right -> value.map (GetPostApiResponse::toPostsEntities)
            }
        }

}