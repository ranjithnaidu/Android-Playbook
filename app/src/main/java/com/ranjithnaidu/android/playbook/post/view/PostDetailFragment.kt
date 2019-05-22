package com.ranjithnaidu.android.playbook.post.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.databinding.FragmentPostDetailBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostDetailViewModel
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.shared.view.RoundedBottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent

class PostDetailFragment : RoundedBottomSheetDialogFragment(), KoinComponent {

    private val postsViewModel: PostDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        arguments?.let { args ->
            args.getString(EXTRA_POST_ID)?.let { postsViewModel.setPostId(it) }
            args.getString(EXTRA_POST_USER_ID)?.let { postsViewModel.setPostUserId(it) }
            args.getString(EXTRA_POST_ID)?.let { postsViewModel.setPostId(it) }
            args.getString(EXTRA_POST_USER_ID)?.let { postsViewModel.setPostUserId(it) }

            postsViewModel.setPostTitleAndBody(
                postTitle = args.getString(EXTRA_POST_TITLE),
                postBody = args.getString(EXTRA_POST_BODY)
            )
        }

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

        fun newInstance(post: Post): PostDetailFragment {
            val bundle = Bundle().apply {
                putString(EXTRA_POST_ID, post.id)
                putString(EXTRA_POST_USER_ID, post.userId)
                putString(EXTRA_POST_BODY, post.body)
                putString(EXTRA_POST_TITLE, post.title)
            }
            return PostDetailFragment().apply { arguments = bundle }
        }
    }

}