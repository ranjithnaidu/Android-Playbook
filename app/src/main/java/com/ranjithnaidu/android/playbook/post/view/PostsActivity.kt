package com.ranjithnaidu.android.playbook.post.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.post.PostsRepository
import com.ranjithnaidu.android.playbook.utils.inTransaction
import com.ranjithnaidu.android.playbook.utils.subscribeAndObserveOnMainThread
import org.koin.android.ext.android.inject

class PostsActivity : AppCompatActivity() {

    private val postsRepository: PostsRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.posts_container, PostsListFragment.newInstance())
            }
        }

        postsRepository.postDetailsUpdates.subscribeAndObserveOnMainThread({
            this.apply {
                val dialog = PostDetailFragment.newInstance(post = it)

                dialog.isCancelable = true
                dialog.show(supportFragmentManager)
            }
        }, {
            Log.e(it.message, it.localizedMessage)
        })

    }
}
