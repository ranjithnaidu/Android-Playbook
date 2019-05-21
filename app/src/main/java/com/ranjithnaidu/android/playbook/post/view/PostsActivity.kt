package com.ranjithnaidu.android.playbook.post.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.databinding.ActivityMainBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent

class PostsActivity : AppCompatActivity(), KoinComponent {

    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = postsViewModel

        initUi(binding)
    }

    private fun initUi(binding: ActivityMainBinding) {
        val postAdapter = setUpRecyclerView(binding.postsRecyclerView, LinearLayoutManager(applicationContext))
        postsViewModel.postsAdapterItems.observe(this, Observer {
            postAdapter.submitList(it)
        })
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager): PostAdapter {
        recyclerView.layoutManager = layoutManager
        val postAdapter = PostAdapter(this)
        recyclerView.adapter = postAdapter
        return postAdapter
    }
}