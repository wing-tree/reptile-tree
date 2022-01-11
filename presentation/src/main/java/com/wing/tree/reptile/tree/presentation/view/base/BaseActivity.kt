package com.wing.tree.reptile.tree.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.view.diary.EditDiaryFragment

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {
    abstract fun inflate(inflater: LayoutInflater): VB

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding!!

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(inflate(layoutInflater)) {
            _viewBinding = this
            setContentView(viewBinding.root)
        }
    }

    protected fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out,
            )
            .setReorderingAllowed(true)
            .replace(R.id.frame_layout_profile, fragment)
            .addToBackStack(null)
            .commit()
    }
}