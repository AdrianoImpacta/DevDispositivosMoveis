package br.com.fernandosousa.lmsapp

import android.arch.persistence.room.Room

object DatabaseManager {
    // singleton
    private var dbInstance: ProdutosDatabase
    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                ProdutosDatabase::class.java, // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco

        ).build()
    }
    fun getProdutoDAO(): ProdutoDAO {
        return dbInstance.produtoDAO()
    }
}