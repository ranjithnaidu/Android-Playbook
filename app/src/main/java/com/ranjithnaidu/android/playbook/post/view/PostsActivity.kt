package com.ranjithnaidu.android.playbook.post.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.utils.inTransaction

class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_posts)

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.posts_container, PostsListFragment.newInstance())
            }
        }
    }
}
