package com.uco.pdm.toolplus.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuarios")
data class Usuario(
    @PrimaryKey val correo: String,
    @ColumnInfo(name = "nombre") var nombre: String? = null,
    @ColumnInfo(name = "cedula") var cedula: String? = null,
    @ColumnInfo(name = "celular") var celular: String? = null,
    @ColumnInfo(name = "direccion") var direccion: String? = null,
    @ColumnInfo(name = "password") var password: String? = null,
    @ColumnInfo(name = "decision") var decision: String? = null
)