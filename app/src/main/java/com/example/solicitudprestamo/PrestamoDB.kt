package com.example.solicitudprestamo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Prestamo::class]
, version = 1)
abstract class PrestamoDB:RoomDatabase() {
    abstract fun prestamoDao():DataPrestamo_Dao


}