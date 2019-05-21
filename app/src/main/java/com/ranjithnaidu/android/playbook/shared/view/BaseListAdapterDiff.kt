package com.ranjithnaidu.android.playbook.shared.view

import androidx.recyclerview.widget.DiffUtil

class BaseListAdapterDiff<U : BaseListAdapterItem> : DiffUtil.ItemCallback<U>() {
    override fun areItemsTheSame(oldItem: U, newItem: U): Boolean {
        return oldItem::class == newItem::class
    }

    override fun areContentsTheSame(oldItem: U, newItem: U): Boolean {
        return oldItem.equals(newItem)
    }
}
