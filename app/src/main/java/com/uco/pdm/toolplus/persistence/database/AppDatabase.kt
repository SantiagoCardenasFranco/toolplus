package com.uco.pdm.toolplus.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uco.pdm.toolplus.models.Compra
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.compra.CompraDAO
import com.uco.pdm.toolplus.persistence.herramienta.HerramientaDAO
import com.uco.pdm.toolplus.persistence.usuario.UsuarioDAO

@Database( entities = [Usuario::class, Herramientas::class, Compra::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "toolplus.db"

        private lateinit var instance: AppDatabase

        fun getInstance(context: Context?): AppDatabase {
            if (::instance.isInitialized) {
                println("Encontré una instancia para conectarte $instance")
                return instance
            } else {
                instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                return instance
            }

        }
    }
    abstract fun usuarioDAO(): UsuarioDAO
    abstract fun herramientaDAO(): HerramientaDAO
    abstract fun compraDAO(): CompraDAO
}