package com.ranjithnaidu.android.playbook.post.view

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.ranjithnaidu.android.playbook.R
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemView

/**
 * This is used to show a loading shimmer while the user's posts are loading
 */
class PostsLoadingShimmerListItemView(
    lifecycleOwner: LifecycleOwner,
    parent: ViewGroup
) : BaseListItemView(lifecycleOwner, R.layout.posts_loading_shimmer_list_item_view, parent) {

    override val viewModel = null
}