package com.example.solicitudprestamo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prestamos")
data class Prestamo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "nombre")val Nombre: String,
    @ColumnInfo(name = "apellido")val Apellido: String,
    @ColumnInfo(name = "cedula")val Cedula: String,
    @ColumnInfo(name = "telefono")val Telefono: Int,
    @ColumnInfo(name = "sexo")val sexo: String,
    @ColumnInfo(name = "ocupacion")val Ocupacion: String,
    @ColumnInfo(name = "direccion")val Direccion: String,
//    @ColumnInfo(name = "monto")val Monto: Int,
//    @ColumnInfo(name = "fechainit")val FechaInit: String,
//    @ColumnInfo(name = "fechafin")val FechaFin: String,
//    @ColumnInfo(name = "montofinal")val MontoFinal: Int,
//    @ColumnInfo(name = "cuotafinal")val CuotaFinal: Int,
//    @ColumnInfo(name = "plazo")val Plazo: Int
)