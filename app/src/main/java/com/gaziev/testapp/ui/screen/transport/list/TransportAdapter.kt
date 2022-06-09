package com.gaziev.testapp.ui.screen.transport.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gaziev.testapp.R
import com.gaziev.testapp.ui.model.Transport

class TransportAdapter(
    private val list: List<Transport>,
    private val click: (name: String?, lat: Double?, lon: Double?) -> Unit
) : RecyclerView.Adapter<TransportHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_transport, parent, false)
        return TransportHolder(view)
    }

    override fun onBindViewHolder(holder: TransportHolder, position: Int) {
        holder.bind(list[position], click)
    }
    override fun getItemCount(): Int = list.size
}