package br.com.fernandosousa.lmsapp

import com.google.gson.GsonBuilder
import java.io.Serializable

class Produto : Serializable {

    var id:Long = 0
    var descricao = ""
    var categoria = ""
    var foto = ""
    var quantidade = ""

    override fun toString(): String {
        return "Produto(nome='$descricao')"
    }
    fun toJson() : String {
        return GsonBuilder().create().toJson(this)
    }
}