package com.example.solicitudprestamo.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.solicitudprestamo.Prestamos
import com.example.solicitudprestamo.R

class RPrestamosAdapter(private val listRegistrosPrestamos:List<Prestamos>) : RecyclerView.Adapter<RegPrestamosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegPrestamosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RegPrestamosViewHolder(layoutInflater.inflate(R.layout.item_prestamo, parent, false))
    }

    override fun onBindViewHolder(holder: RegPrestamosViewHolder, position: Int) {
        val item = listRegistrosPrestamos[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listRegistrosPrestamos.size
}