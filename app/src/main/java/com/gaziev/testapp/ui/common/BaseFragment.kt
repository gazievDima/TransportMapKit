package com.gaziev.testapp.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.gaziev.testapp.R
import com.gaziev.testapp.ui.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    abstract val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as MainActivity).toolbarInitialize(this)

        _binding = inflate.invoke(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun snackbar(msg: String, time: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(binding.root, msg, time).show()
    }

    fun navigateTo(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, fragment)
            .commit()
    }


    fun navigateToWithBackStack(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, fragment)
            .addToBackStack(null)
            .commit()
    }

}