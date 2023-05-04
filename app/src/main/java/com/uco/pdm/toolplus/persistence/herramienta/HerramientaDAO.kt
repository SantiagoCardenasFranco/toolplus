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

    @Query("UPDATE tabla_herramientas SET nombre = :nombre," +
            "description = :description, precio = :precio, cantidad = :cantidad," +
            "url = :url WHERE herramientaId = :id")
    fun update(nombre: String, description: String, precio: Int, cantidad: Int,
               url: String, id: Int)

    @Query("DELETE FROM tabla_herramientas WHERE herramientaId = :id")
    fun deleteAll(id: Int)
}