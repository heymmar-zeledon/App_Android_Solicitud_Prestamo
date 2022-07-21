package com.example.solicitudprestamo

import java.util.*

data class Prestamos(
    val Nombre: String,
    val Apellido: String,
    val Monto: Int,
    val FechaInit: Date,
    val FechaFin: Date,
    val Estado: String)