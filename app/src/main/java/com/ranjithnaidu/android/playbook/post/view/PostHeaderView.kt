package com.ranjithnaidu.android.playbook.post.view

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.databinding.PostHeaderListItemViewBinding
import com.ranjithnaidu.android.playbook.post.viewmodel.PostHeaderViewModel
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemView
import org.koin.standalone.inject

/**
 * This is used to display the different header
 */
class PostHeaderView(
    lifecycleOwner: LifecycleOwner,
    parent: ViewGroup
) : BaseListItemView(lifecycleOwner, R.layout.post_header_list_item_view, parent) {

    override val viewModel: PostHeaderViewModel by inject()

    init {
        (binding as PostHeaderListItemViewBinding).viewModel = viewModel
    }
}