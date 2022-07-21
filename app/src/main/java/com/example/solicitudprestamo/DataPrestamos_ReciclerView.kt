package com.example.solicitudprestamo

data class DataPrestamos_ReciclerView(
    val Nombre: String,
    val Apellido: String,
    val Cedula: String,
    val Telefono: Int,
    val sexo: String,
    val Ocupacion: String,
    val Direccion: String,
    val Monto: Int,
    val FechaInit: String,
    val FechaFin: String,
//    val Estado: String,
    val MontoFinal: Int,
    val CuotaFinal: Int,
    val Plazo: Int
)