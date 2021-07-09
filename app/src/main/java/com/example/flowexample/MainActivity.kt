package com.example.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.flowexample.core.adapters.OnItemClickListener
import com.example.flowexample.core.adapters.WeatherAdapter
import com.example.flowexample.databinding.ActivityMainBinding
import com.example.flowexample.features.posts.domain.entity.PostEntity
import com.example.flowexample.features.posts.presentation.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClickListener<PostEntity> {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    lateinit var mainBinding: ActivityMainBinding
    lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mainBinding.root)
        bindViews()
        setUpAdapter()

        mainActivityViewModel.allItems.observe(this@MainActivity, ::setData)
    }

    private fun setUpAdapter() {
        weatherAdapter = WeatherAdapter(this)
        mainBinding.rvMain.adapter = weatherAdapter
    }

    private fun bindViews() {
        mainBinding.apply {
            vm = mainActivityViewModel
            lifecycleOwner = this@MainActivity
            executePendingBindings()
        }
    }

    override fun onItemClick(item: PostEntity) {

    }

    private fun setData(items: List<PostEntity>) {
        weatherAdapter.submitList(items)
    }


}