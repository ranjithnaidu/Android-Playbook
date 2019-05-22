package com.ranjithnaidu.android.playbook.post.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.post.viewmodel.PostListItemViewModel
import com.ranjithnaidu.android.playbook.utils.inTransaction
import com.ranjithnaidu.android.playbook.utils.subscribeAndObserveOnMainThread
import org.koin.android.ext.android.inject
import org.koin.standalone.KoinComponent

class PostsActivity : AppCompatActivity(), KoinComponent {

    private val postListItemViewModel: PostListItemViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.posts_container, PostsListFragment.newInstance())
            }
        }

        postListItemViewModel.postUpdates.subscribeAndObserveOnMainThread({
            supportFragmentManager.inTransaction {
                add(R.id.posts_container, PostDetailActivity.newInstance(post = it))
            }
        }, { })

    }
}
