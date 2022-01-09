package com.wing.tree.reptile.tree.presentation.view.main

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import com.wing.tree.reptile.tree.eventbus.OnBackPressedEventBus
import com.wing.tree.reptile.tree.presentation.BuildConfig
import com.wing.tree.reptile.tree.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(window) {
            allowEnterTransitionOverlap = true
            allowReturnTransitionOverlap = true

            sharedElementEnterTransition = TransitionInflater.from(applicationContext)
                .inflateTransition(R.transition.shared_element_enter_transition)

            sharedElementExitTransition = TransitionInflater.from(applicationContext)
                .inflateTransition(R.transition.shared_element_exit_transition)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        compositeDisposable.add(
            OnBackPressedEventBus.subscribe().subscribe {
                onBackPressed()
            }
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}