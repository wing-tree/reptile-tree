package com.wing.tree.reptile.tree.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.coroutineScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {
    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): VB

    private var _viewBinding: VB? = null
    protected val viewBinding: VB
        get() = _viewBinding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = inflate(inflater, container)

        return viewBinding.root
    }

    @CallSuper
    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

    protected fun delayOnLifecycle(
        timeMillis: Long,
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        block: () -> Unit
    ) {
        viewLifecycleOwner.lifecycle.coroutineScope.launch(dispatcher) {
            delay(timeMillis)
            block()
        }
    }
}