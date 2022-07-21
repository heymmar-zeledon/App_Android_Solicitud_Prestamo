package com.example.solicitudprestamo

import androidx.room.*

@Dao
interface DataPrestamo_Dao {
    @Query("SELECT * from Prestamos")
    suspend fun getAll(): List<Prestamo>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(prestamo: Prestamo)
    @Delete
    fun delete(pre:Prestamo)


}