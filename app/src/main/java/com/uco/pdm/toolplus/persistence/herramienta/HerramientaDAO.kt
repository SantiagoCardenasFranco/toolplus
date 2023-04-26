package com.uco.pdm.toolplus.persistence.herramienta

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.models.Usuario

@Dao
interface HerramientaDAO {

    @Query("SELECT * FROM tabla_herramientas")
    fun getAll(): List<Herramientas>

    @Insert
    fun insertAll(vararg herramientas: Herramientas)

    @Update
    fun update(herramientas: Herramientas)

    @Query("DELETE FROM tabla_herramientas")
    fun deleteAll()
}