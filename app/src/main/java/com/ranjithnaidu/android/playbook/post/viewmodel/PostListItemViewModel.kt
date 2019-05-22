package com.ranjithnaidu.android.playbook.post.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ranjithnaidu.android.playbook.post.PostsRepository
import com.ranjithnaidu.android.playbook.post.view.PostAdapter
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.shared.view.BaseListAdapterItem
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemViewModel
import org.koin.standalone.inject

/**
 * This is the view model used to display the different posts
 */
class PostListItemViewModel : BaseListItemViewModel() {

    private val postsRepository: PostsRepository by inject()

    val postTitle = MutableLiveData<String>()
    var post: Post? = null

    override fun setData(listAdapterItem: BaseListAdapterItem) {
        if (listAdapterItem !is PostAdapter.PostAdapterItem.PostItem) return
        this.post = listAdapterItem.post
        postTitle.postValue(listAdapterItem.post.title)
    }

    fun onPostClicked() {
        post?.let {
            postsRepository.openPostDetail(it)
        }
    }
}