package com.ranjithnaidu.android.playbook.shared.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import org.koin.standalone.KoinComponent

/**
 * This is the base view for a list item view which takes care of inflating the layout
 * and creating the binding. Overriding classes will need to override and create the viewModel and
 * set the [ViewDataBinding]'s view model. For example in a CustomListItemView you might have:
 *
 * override val viewModel: CustomListItemViewModel by inject()
 * init {
 *      (binding as CustomListItemViewBinding).viewModel = viewModel
 * }
 */
abstract class BaseListItemView(
    lifecycleOwner: LifecycleOwner,
    layoutId: Int,
    parent: ViewGroup
) : FrameLayout(parent.context), KoinComponent {

    abstract val viewModel: BaseListItemViewModel?
    protected val binding: ViewDataBinding

    open fun setData(listAdapterItem: BaseListAdapterItem) {
        viewModel?.setData(listAdapterItem)
    }

    init {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(
                layoutInflater,
                layoutId,
                this,
                true)
        binding.lifecycleOwner = lifecycleOwner
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}

abstract class BaseListAdapterItem {
    val type = this::class.hashCode()
}

open class BaseListItemViewHolder(protected val view: BaseListItemView) : RecyclerView.ViewHolder(view) {
    fun setData(listAdapterItem: BaseListAdapterItem) {
        view.setData(listAdapterItem)
    }
}

/**
 * Each new list item view will need to create a corresponding ViewModel that extends this class
 * and provides the data required by the view
 */
abstract class BaseListItemViewModel : ViewModel(), KoinComponent {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    abstract fun setData(listAdapterItem: BaseListAdapterItem)
}