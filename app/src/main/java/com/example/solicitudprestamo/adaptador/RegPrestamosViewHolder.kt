package com.example.solicitudprestamo.adaptador

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.solicitudprestamo.DataPrestamos_ReciclerView
import com.example.solicitudprestamo.R

class RegPrestamosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val nombre: TextView = view.findViewById(R.id.ClienteNombre)
    private val monto: TextView = view.findViewById(R.id.Monto)
    private val fechaInit: TextView = view.findViewById(R.id.FechaInit)
    private val fechaFin: TextView = view.findViewById(R.id.FechaFin)
    private val status: TextView = view.findViewById(R.id.Estado)
    private val previewPhoto: ImageView = view.findViewById(R.id.ImagePreview)

    @SuppressLint("SetTextI18n")
    fun render(modelosDatosPrestamo: DataPrestamos_ReciclerView){
        nombre.text = modelosDatosPrestamo.Nombre + " "+ modelosDatosPrestamo.Apellido
        monto.text = modelosDatosPrestamo.Monto.toString()
        fechaInit.text = modelosDatosPrestamo.FechaInit
        fechaFin.text = modelosDatosPrestamo.FechaFin
        previewPhoto.setImageResource(R.drawable._14_1149972_avatar_free_png_image_avatar_png)
        //status.text = modelosDatosPrestamo.Estado
    }

}