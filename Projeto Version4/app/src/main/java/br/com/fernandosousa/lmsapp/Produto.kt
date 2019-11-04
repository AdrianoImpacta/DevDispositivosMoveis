package br.com.fernandosousa.lmsapp

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_add_produto.*
import java.io.Serializable

@Entity(tableName = "produto")
class Produto : Serializable {

    @PrimaryKey
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