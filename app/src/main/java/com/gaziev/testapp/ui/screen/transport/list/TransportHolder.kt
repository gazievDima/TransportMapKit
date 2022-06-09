package com.gaziev.testapp.ui.screen.transport.list

import android.graphics.Typeface
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gaziev.testapp.R
import com.gaziev.testapp.databinding.ItemTransportBinding
import com.gaziev.testapp.ui.model.Transport

class TransportHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemTransportBinding by lazy { ItemTransportBinding.bind(itemView) }

    fun bind(item: Transport, click: (name: String?, lat: Double?, lon: Double?) -> Unit) {
        if (item.root) showRootTransport(item) else showTransport(item, click)
    }

    private fun showRootTransport(item: Transport) {
        binding.tvTitle.apply {
            text = item.rootName
            setTypeface(null, Typeface.BOLD)
            setBackgroundColor(
                itemView.resources.getColor(
                    R.color.alpha_0,
                    itemView.resources.newTheme())
            )
            layoutParams.height = 100
        }
    }

    private fun showTransport
                (item: Transport, click: (name: String?, lat: Double?, lon: Double?) -> Unit) {
        val str = "|-- ${item.nameTransport}"
        with(binding) {
            tvTitle.text = str
            tvTitle.setPadding(120, 0, 0, 0)
            tvTitle.setOnClickListener {
                click(item.nameTransport, item.lat, item.lon)
            }
        }
    }
}