package com.ranjithnaidu.android.playbook.shared.view

import android.app.Dialog
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ranjithnaidu.android.playbook.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Bottom Sheet Dialog with rounded top edges
 */
open class RoundedBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val backPressesSubject = PublishSubject.create<Unit>()
    val backPresses: Observable<Unit> = backPressesSubject.hide()

    // Used for rounding the edges
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : BottomSheetDialog(requireContext(), theme) {
            override fun onBackPressed() {
                super.onBackPressed()
                backPressesSubject.onNext(Unit)
            }
        }

        dialog.setOnShowListener {

            val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    fun show(supportFragmentManager: FragmentManager) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(this, tag)
        fragmentTransaction.commitAllowingStateLoss()
    }
}