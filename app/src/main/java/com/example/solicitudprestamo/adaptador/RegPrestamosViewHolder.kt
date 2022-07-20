package com.example.solicitudprestamo.adaptador

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.solicitudprestamo.Prestamos
import com.example.solicitudprestamo.R

class RegPrestamosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val nombre: TextView = view.findViewById(R.id.ClienteNombre)
    private val monto: TextView = view.findViewById(R.id.Monto)
    private val fechaInit: TextView = view.findViewById(R.id.FechaInit)
    private val fechaFin: TextView = view.findViewById(R.id.FechaFin)
    private val status: TextView = view.findViewById(R.id.Estado)

    @SuppressLint("SetTextI18n")
    fun render(modelosDatosPrestamo: Prestamos){
        nombre.text = modelosDatosPrestamo.Nombre + " "+ modelosDatosPrestamo.Apellido
        monto.text = modelosDatosPrestamo.Monto.toString()
        fechaInit.text = modelosDatosPrestamo.FechaInit.toString()
        fechaFin.text = modelosDatosPrestamo.FechaFin.toString()
        status.text = modelosDatosPrestamo.Estado
    }

}