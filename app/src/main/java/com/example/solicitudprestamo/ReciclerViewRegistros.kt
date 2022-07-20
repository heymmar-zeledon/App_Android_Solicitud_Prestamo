package com.example.solicitudprestamo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.solicitudprestamo.adaptador.RPrestamosAdapter

class ReciclerViewRegistros : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciclerview_registros)
        iniciarReciclerView()
    }

    private fun iniciarReciclerView(){
        val manager =  LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        val reciclerView = findViewById<RecyclerView>(R.id.registrosPrestamos)
        reciclerView.layoutManager = manager
        reciclerView.adapter = RPrestamosAdapter(RegistrosProvider.listprestamos)
        reciclerView.addItemDecoration(decoration)
    }

}