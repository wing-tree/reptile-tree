package com.wing.tree.reptile.tree.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

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
}