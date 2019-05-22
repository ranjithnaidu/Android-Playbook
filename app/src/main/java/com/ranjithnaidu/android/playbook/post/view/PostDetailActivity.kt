package com.ranjithnaidu.android.playbook.post.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.databinding.FragmentPostDetailBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostDetailViewModel
import com.ranjithnaidu.android.playbook.services.models.Post
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent

class PostDetailActivity : BottomSheetDialogFragment(), KoinComponent {

    private val postsViewModel: PostDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        postsViewModel.setPostId(requireNotNull(arguments).getString(EXTRA_POST_ID))
        postsViewModel.setPostUserId(requireNotNull(arguments).getString(EXTRA_POST_USER_ID))
        postsViewModel.setPostTitleAndBody(
            postTitle = requireNotNull(arguments).getString(EXTRA_POST_TITLE),
            postBody = requireNotNull(arguments).getString(EXTRA_POST_BODY)
        )

        val binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = postsViewModel

        initUi(binding)

        return binding.root
    }

    private fun initUi(binding: FragmentPostDetailBinding) {
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

        fun newInstance(post: Post): PostDetailActivity {
            val bundle = Bundle().apply {
                putString(EXTRA_POST_ID, post.id)
                putString(EXTRA_POST_USER_ID, post.userId)
                putString(EXTRA_POST_BODY, post.body)
                putString(EXTRA_POST_TITLE, post.title)
            }
            return PostDetailActivity().apply { arguments = bundle }
        }
    }

}