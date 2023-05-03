package com.uco.pdm.toolplus.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_herramientas")
data class Herramientas(
    @ColumnInfo(name = "nombre") var nombre: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "precio") var precio: Int? = null,
    @ColumnInfo(name = "cantidad") var cantidad: Int? = null,
    @ColumnInfo(name = "url") var url: String
){
    @PrimaryKey(autoGenerate = true)
    var herramientaId: Int = 0
}