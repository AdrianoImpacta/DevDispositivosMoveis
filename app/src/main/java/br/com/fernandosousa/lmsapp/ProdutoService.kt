package br.com.fernandosousa.lmsapp

import android.content.Context

object ProdutoService {

    fun getProdutos (context: Context): List<Produto> {
        val produtos = mutableListOf<Produto>()

        // criar 10 produtos
        for (i in 1..10) {
            val d = Produto()
            d.descricao = "Produto $i"
            d.categoria = "Categoria Produto $i"
            d.quantidade = "Quantidade Produto $i"
            d.foto = "https://sc2.elpais.com.uy/files/article_default_content/uploads/2017/09/10/59b59de8c4b6a.jpeg"
            produtos.add(d)
        }

        return produtos
    }

}