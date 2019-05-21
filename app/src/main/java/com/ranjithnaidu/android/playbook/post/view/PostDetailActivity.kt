package com.ranjithnaidu.android.playbook.post.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.databinding.ActivityPostDetailBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostDetailViewModel
import com.ranjithnaidu.android.playbook.services.models.Post
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent

class PostDetailActivity : AppCompatActivity(), KoinComponent {

    private val postsViewModel: PostDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postsViewModel.setPostId(intent.getStringExtra(EXTRA_POST_ID))
        postsViewModel.setPostUserId(intent.getStringExtra(EXTRA_POST_USER_ID))
        postsViewModel.setPostTitleAndBody(
            postTitle = intent.getStringExtra(EXTRA_POST_TITLE),
            postBody = intent.getStringExtra(EXTRA_POST_BODY)
        )

        val binding: ActivityPostDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
        binding.lifecycleOwner = this
        binding.viewModel = postsViewModel

        initUi(binding)
    }

    private fun initUi(binding: ActivityPostDetailBinding) {
        postsViewModel.authorName.observe(this, Observer {
            binding.postAuthorName.text = getString(R.string.author_name, it)
        })

        postsViewModel.noOfComments.observe(this, Observer {
            binding.postComments.text = getString(R.string.number_of_comments, it)
        })
    }

    companion object {
        private const val EXTRA_POST_ID = "POST_ID"
        private const val EXTRA_POST_USER_ID = "POST_USER_ID"
        private const val EXTRA_POST_BODY = "POST_BODY"
        private const val EXTRA_POST_TITLE = "POST_TITLE"

        fun starterIntent(
            context: Context,
            post: Post
        ): Intent {
            return Intent(context, PostDetailActivity::class.java).apply {
                putExtra(EXTRA_POST_ID, post.id)
                putExtra(EXTRA_POST_USER_ID, post.userId)
                putExtra(EXTRA_POST_BODY, post.body)
                putExtra(EXTRA_POST_TITLE, post.title)
            }
        }
    }

}