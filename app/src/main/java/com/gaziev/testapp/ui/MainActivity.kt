package com.gaziev.testapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gaziev.testapp.R
import com.gaziev.testapp.databinding.ActivityMainBinding
import com.gaziev.testapp.ui.view.ToolbarView

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarInitialize(fragment: Fragment) {
        if(fragment is ToolbarView) {
            binding.toolbar.title = fragment.getTitle()
            binding.toolbar.visibility = View.VISIBLE
        } else {
            binding.toolbar.visibility = View.GONE
        }
    }
}


