package br.com.fernandosousa.lmsapp

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
}