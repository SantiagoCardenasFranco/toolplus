package com.uco.pdm.toolplus.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_compra")
data class Compra(
    @ColumnInfo(name = "correo") var correo: String? = null,
    @ColumnInfo(name = "herramienta") var herramienta: Int? = null
){
    @PrimaryKey(autoGenerate = true)
    var compraId: Int = 0
}