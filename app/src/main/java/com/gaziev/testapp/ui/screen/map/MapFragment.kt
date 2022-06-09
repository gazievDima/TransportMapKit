package com.gaziev.testapp.ui.screen.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.gaziev.testapp.App
import com.gaziev.testapp.R
import com.gaziev.testapp.databinding.FrMapBinding
import com.gaziev.testapp.ui.common.BaseFragment
import com.gaziev.testapp.ui.view.ToolbarView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.ui_view.ViewProvider
import javax.inject.Inject


class MapFragment : BaseFragment<FrMapBinding>(), ToolbarView {
    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> FrMapBinding =
        FrMapBinding::inflate

    override fun getTitle(): String = "Map"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MapViewModel by activityViewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireContext());
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)

        viewModel.coordinates.observe(viewLifecycleOwner) {
            binding.tvResult.text = it.name
            moveMap(it.lat, it.lon)
            setDot(it.lat, it.lon)
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    private fun moveMap(lat: Double, lon: Double) {
        binding.mapview.map
            .move(
                CameraPosition(
                    Point(lat, lon), 15.0f, 0.0f, 0.0f
                ),
                Animation(Animation.Type.SMOOTH, 0F),
                null
            )
    }

    private fun setDot(lat: Double, lon: Double) {
        val icon = ImageView(requireContext())
        icon.setImageResource(R.drawable.ic_baseline_location_on_24)
        val viewProvider = ViewProvider(icon)
        val mapObjects = binding.mapview.map.mapObjects.addCollection()

        val viewPlace: PlacemarkMapObject =
            mapObjects.addPlacemark(Point(lat, lon), viewProvider)
        viewPlace.setView(viewProvider)
    }

}