package com.ranjithnaidu.android.playbook.post.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ranjithnaidu.android.playbook.post.PostsRepository
import com.ranjithnaidu.android.playbook.shared.viewmodel.BaseViewModel
import com.ranjithnaidu.android.playbook.utils.setValueIfNew
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PostDetailViewModel : BaseViewModel(), KoinComponent {

    private val postsRepository: PostsRepository by inject()

    val postId = MutableLiveData<String>()
    val postUserId = MutableLiveData<String>()
    val postBody = MutableLiveData<String>()
    val postTitle = MutableLiveData<String>()

    val noOfComments = MutableLiveData<Int>()
    val authorName = MutableLiveData<String>()

    private fun loadComments(postId: String) {
        postsRepository.loadNoOfCommentsForPost(postId)
            .subscribe({
                noOfComments.postValue(it)
            }, { Log.e(it.message, it.toString()) }).autoDispose()
    }

    private fun loadAuthor(userId: String) {
        postsRepository.loadAuthorNameForPost(userId)
            .subscribe({ author ->
                authorName.postValue(author)
            }, { Log.e(it.message, it.toString()) }).autoDispose()
    }

    fun setPostId(postId: String) {
        this.postId.setValueIfNew(postId)
        loadComments(postId)
    }

    fun setPostUserId(postUserId: String) {
        this.postUserId.setValueIfNew(postUserId)
        loadAuthor(postUserId)
    }

    fun setPostTitleAndBody(postTitle: String, postBody: String) {
        this.postTitle.setValueIfNew(postTitle)
        this.postBody.setValueIfNew(postBody)
    }
}