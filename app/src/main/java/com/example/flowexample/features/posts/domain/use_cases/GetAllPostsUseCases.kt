package com.example.flowexample.features.posts.domain.use_cases

import com.example.flowexample.core.failure.Failure
import com.example.flowexample.core.helpers.Either
import com.example.flowexample.core.usecase.UseCaseNoParams
import com.example.flowexample.features.posts.domain.entity.PostEntity
import com.example.flowexample.features.posts.domain.repo.PostRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostsUseCases @Inject constructor(private val postRepo: PostRepo) :
    UseCaseNoParams<List<PostEntity>> {
    override suspend fun invoke(): Flow<Either<Failure, List<PostEntity>>> {
        return postRepo.getAllPosts()
    }


}