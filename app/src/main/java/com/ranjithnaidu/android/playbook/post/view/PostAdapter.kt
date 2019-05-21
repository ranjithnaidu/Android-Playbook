package com.ranjithnaidu.android.playbook.post.view

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import com.ranjithnaidu.android.playbook.services.models.Post
import com.ranjithnaidu.android.playbook.shared.view.BaseListAdapterItem
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemViewHolder
import com.ranjithnaidu.android.playbook.shared.view.BaseListAdapterDiff

/**
 * List adapter for the home screen that shows posts
 */
class PostAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<PostAdapter.PostAdapterItem, BaseListItemViewHolder>(
        BaseListAdapterDiff<PostAdapterItem>()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListItemViewHolder {
        return when (viewType) {
            PostAdapterItem.PostHeaderItem::class.hashCode() -> {
                BaseListItemViewHolder(
                    PostHeaderView(
                        lifecycleOwner,
                        parent
                    )
                )
            }
            PostAdapterItem.PostItem::class.hashCode() -> {
                BaseListItemViewHolder(
                    PostListItemView(
                        lifecycleOwner,
                        parent
                    )
                )
            }
            PostAdapterItem.PostsLoadingShimmerItem::class.hashCode() -> {
                BaseListItemViewHolder(
                    PostsLoadingShimmerListItemView(
                        lifecycleOwner,
                        parent
                    )
                )
            }
            else -> throw Exception("Unrecognized view type")
        }
    }

    override fun onBindViewHolder(holder: BaseListItemViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    sealed class PostAdapterItem : BaseListAdapterItem() {
        class PostHeaderItem(val title: String?) : PostAdapterItem()
        class PostItem(val post: Post) : PostAdapterItem()
        class PostsLoadingShimmerItem : PostAdapterItem()
    }
}
