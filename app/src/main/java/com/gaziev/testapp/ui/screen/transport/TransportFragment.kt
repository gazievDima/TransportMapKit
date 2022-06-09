package com.gaziev.testapp.ui.screen.transport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaziev.testapp.App
import com.gaziev.testapp.R
import com.gaziev.testapp.databinding.FrTransportBinding
import com.gaziev.testapp.ui.common.BaseFragment
import com.gaziev.testapp.ui.common.StateUI
import com.gaziev.testapp.ui.screen.map.MapFragment
import com.gaziev.testapp.ui.screen.map.MapViewModel
import com.gaziev.testapp.ui.screen.transport.list.TransportAdapter
import com.gaziev.testapp.ui.view.ToolbarView
import javax.inject.Inject

class TransportFragment : BaseFragment<FrTransportBinding>(), ToolbarView {
    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FrTransportBinding =
        FrTransportBinding::inflate
    override fun getTitle(): String = "Transport"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TransportViewModel by viewModels { viewModelFactory }
    private val viewModelMap: MapViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)
        observed()
    }

    private fun observed() {
        viewModel.listTransport.observe(viewLifecycleOwner) {
            binding.recycler.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                adapter = TransportAdapter(it) { name: String?, lat: Double?, lon: Double? ->
                    if (name != null && lat != null && lon != null) {
                        viewModelMap.saveCoordinates(name, lat, lon)
                        navigateToWithBackStack(MapFragment())
                    } else {
                        snackbar(getString(R.string.not_found_coordinates))
                    }
                }
            }
        }

        viewModel.stateUI.observe(viewLifecycleOwner) {
            when (it) {
                is StateUI.Loading -> {
                    binding.apply {
                        loading.container.visibility = View.VISIBLE
                        success.visibility = View.GONE
                        error.container.visibility = View.GONE
                    }
                }
                is StateUI.Error -> {
                    binding.apply {
                        loading.container.visibility = View.GONE
                        success.visibility = View.GONE
                        error.container.visibility = View.VISIBLE
                    }
                }
                is StateUI.Success -> {
                    binding.apply {
                        loading.container.visibility = View.GONE
                        success.visibility = View.VISIBLE
                        error.container.visibility = View.GONE
                    }
                }
            }
        }
    }
}