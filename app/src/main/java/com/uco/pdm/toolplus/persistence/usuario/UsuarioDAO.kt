package com.uco.pdm.toolplus.persistence.usuario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.uco.pdm.toolplus.models.Usuario

@Dao
interface UsuarioDAO {

    @Query("SELECT * FROM tabla_usuarios")
    fun getAll(): List<Usuario>

    @Insert
    fun insertAll(vararg usuario: Usuario)

    @Update
    fun update(usuario: Usuario)

    @Query("DELETE FROM tabla_usuarios")
    fun deleteAll()
}