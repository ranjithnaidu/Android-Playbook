package com.ranjithnaidu.android.playbook.post.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranjithnaidu.android.playbook.databinding.FragmentPostsListsBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostListItemViewModel
import com.ranjithnaidu.android.playbook.post.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent

class PostsListFragment : Fragment(), KoinComponent {

    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPostsListsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = postsViewModel

        initUi(binding)

        return binding.root
    }

    private fun initUi(binding: FragmentPostsListsBinding) {
        val postAdapter = setUpRecyclerView(binding.postsRecyclerView, LinearLayoutManager(context))
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

    companion object {
        fun newInstance(): PostsListFragment {
            return PostsListFragment()
        }
    }
}