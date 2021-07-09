package com.example.flowexample.core.di

import com.example.flowexample.features.posts.data.repo_impl.PostRepoImpl
import com.example.flowexample.features.posts.domain.repo.PostRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindPostRepo(impl: PostRepoImpl): PostRepo
}