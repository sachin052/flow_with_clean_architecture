package com.example.flowexample.features.posts.presentation

import androidx.lifecycle.*
import com.example.flowexample.core.helpers.Either
import com.example.flowexample.core.viewmodel.MyViewModel
import com.example.flowexample.features.posts.domain.repo.PostRepo
import com.example.flowexample.features.posts.domain.use_cases.GetAllPostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getAllPostsUseCases: GetAllPostsUseCases,private val postRepo: PostRepo)  : MyViewModel() {

    val allItems = executeApi { getAllPostsUseCases.invoke() }.map { value ->  when(value){
        is Either.Left -> emptyList()
        is Either.Right -> value.r
    } }.asLiveData()

//    fun getItems(){
//
//        viewModelScope.launch{
//            postRepo.getAllPosts().collect {
//                print(it)
//            }
//        }
//    }
}