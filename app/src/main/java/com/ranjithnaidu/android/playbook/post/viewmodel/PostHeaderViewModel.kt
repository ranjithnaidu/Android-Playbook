package com.ranjithnaidu.android.playbook.post.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ranjithnaidu.android.playbook.post.view.PostAdapter
import com.ranjithnaidu.android.playbook.shared.view.BaseListAdapterItem
import com.ranjithnaidu.android.playbook.shared.view.BaseListItemViewModel

/**
 * This is the view model used to display the header
 */
class PostHeaderViewModel : BaseListItemViewModel() {

    val headerTitle = MutableLiveData<String>()

    override fun setData(listAdapterItem: BaseListAdapterItem) {
        if (listAdapterItem !is PostAdapter.PostAdapterItem.PostHeaderItem) return
        this.headerTitle.postValue(listAdapterItem.title)
    }
}