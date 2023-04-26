package com.uco.pdm.toolplus.persistence.compra

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uco.pdm.toolplus.models.Compra
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.models.Usuario

@Dao
interface CompraDAO {

    @Query("SELECT * FROM tabla_compra")
    fun getAll(): List<Compra>

    @Insert
    fun insertAll(vararg compra: Compra)

    @Query("DELETE FROM tabla_compra")
    fun delete()
}