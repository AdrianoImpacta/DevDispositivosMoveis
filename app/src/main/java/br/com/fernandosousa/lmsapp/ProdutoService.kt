package br.com.fernandosousa.lmsapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutoService {

    val host = "https://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getProdutos (context: Context): List<Produto> {
        val produtos = mutableListOf<Produto>()

        val url = "$host/disciplinas"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        return parseJson<List<Produto>>(json)

    }

    fun save(produto: Produto): Response {
        val produto_json = produto.toJson()
        val retorno = HttpHelper.post("$host/disciplinas", produto_json)

        return parseJson<Response>(retorno)
    }

    inline fun <reified T> parseJson(json: String) : T{
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}