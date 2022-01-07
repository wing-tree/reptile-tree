package com.wing.tree.reptile.tree.presentation.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wing.tree.reptile.tree.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}