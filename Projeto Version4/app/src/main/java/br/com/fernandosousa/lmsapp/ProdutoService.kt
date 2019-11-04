package br.com.fernandosousa.lmsapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.Toast
import android.widget.Toast.makeText

object ProdutoService {

    val host = "https://adrianoangelo.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getProdutos (context: Context): List<Produto> {
        var produtos = ArrayList<Produto>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/produtos"
            val json = HttpHelper.get(url)
            produtos = parseJson(json)
            // salvar offline
            for (p in produtos) {
                saveOffline(p)
            }
            return produtos
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produtos = dao.findAll()
            return produtos
        }
    }

    fun save(produto: Produto): Boolean {
        val json = HttpHelper.post("$host/produtos", produto.toJson())
        return parseJson(json)
    }

    fun saveOffline(produto: Produto) : Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        if (! existeProduto(produto)) {
            dao.insert(produto)
        }
        return true
    }

    fun existeProduto(produto: Produto): Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        return dao.getById(produto.id) != null

    }

    fun delete(produto: Produto): Response {
        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)) {
            val url = "$host/produtos/${produto.id}"
            val json = HttpHelper.delete(url)
            return parseJson(json)
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            dao.delete(produto)
            return Response(status = "OK", msg = "Dados deletados localmente")
        }
    }

    inline fun <reified T> parseJson(json: String) : T{
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}