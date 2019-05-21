package com.ranjithnaidu.android.playbook.post.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ranjithnaidu.android.playbook.post.view.PostAdapter
import com.ranjithnaidu.android.playbook.services.PlaybookService
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.shared.viewmodel.BaseViewModel
import org.koin.standalone.inject

class PostsViewModel : BaseViewModel() {

    private val playbookService: PlaybookService by inject()

    private var posts: List<Post> = listOf()
    val postsAdapterItems = MutableLiveData<List<PostAdapter.PostAdapterItem>>()

    var postsLoading = true

    init {
        loadPosts()
    }

    private fun loadPosts() {
        playbookService.loadPosts()
            .doOnSubscribe {
                postsLoading = true
                updatePostsAdapter()
            }
            .doAfterTerminate {
                postsLoading = false
                updatePostsAdapter()
            }
            .subscribe({
                posts = it
                updatePostsAdapter()
            }, { Log.e(it.message, it.toString()) }).autoDispose()
    }

    private fun updatePostsAdapter() {

        val newList = mutableListOf<PostAdapter.PostAdapterItem>()

        if (postsLoading) {
            newList.add(PostAdapter.PostAdapterItem.PostsLoadingShimmerItem())
        } else {
            // TODO remove hardcoded string
            newList.add(PostAdapter.PostAdapterItem.PostHeaderItem(title = "Posts"))
            newList.addAll(posts.map { post -> PostAdapter.PostAdapterItem.PostItem(post) })
        }

        postsAdapterItems.postValue(newList)
    }
}