package com.uco.pdm.toolplus.utils

import com.uco.pdm.toolplus.models.Herramientas

class UtilsArray {

    fun append(arr: Array<Herramientas>, mensaje: Herramientas): Array<Herramientas> {
        val list: MutableList<Herramientas> = arr.toMutableList()
        list.add(mensaje)
        return list.toTypedArray()
    }
}