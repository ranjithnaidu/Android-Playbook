package com.ranjithnaidu.android.playbook.post.view

import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.databinding.PostTitleListItemViewBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostListItemViewModel
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemView
import com.ranjithnaidu.android.playbook.utils.subscribeToViewModelObservable
import com.ranjithnaidu.android.playbook.utils.throttleClicks
import org.koin.standalone.inject

/**
 * This is used to display the different posts on home
 */
class PostListItemView(
    lifecycleOwner: LifecycleOwner,
    parent: ViewGroup
) : BaseListItemView(lifecycleOwner, R.layout.post_title_list_item_view, parent) {

    override val viewModel: PostListItemViewModel by inject()

    init {
        (binding as PostTitleListItemViewBinding).viewModel = viewModel
        binding.postTitle.throttleClicks().subscribeToViewModelObservable {
            viewModel.onPostClicked()
        }
    }
}
