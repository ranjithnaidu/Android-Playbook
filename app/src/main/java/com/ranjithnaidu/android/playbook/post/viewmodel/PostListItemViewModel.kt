package com.ranjithnaidu.android.playbook.post.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ranjithnaidu.android.playbook.post.view.PostAdapter
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.shared.view.BaseListAdapterItem
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * This is the view model used to display the different posts
 */
class PostListItemViewModel : BaseListItemViewModel() {

    private val postClickedSubject = PublishSubject.create<Post>()
    val postUpdates: Observable<Post> = postClickedSubject.hide()

    val postTitle = MutableLiveData<String>()
    var post: Post? = null

    override fun setData(listAdapterItem: BaseListAdapterItem) {
        if (listAdapterItem !is PostAdapter.PostAdapterItem.PostItem) return
        this.post = listAdapterItem.post
        postTitle.postValue(listAdapterItem.post.title)
    }

    fun onPostClicked(view: View) {
        post?.let {
            postClickedSubject.onNext(it)
        }
    }
}