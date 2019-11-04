package br.com.fernandosousa.lmsapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Produto::class), version = 1)
abstract class ProdutosDatabase: RoomDatabase() {
    abstract fun produtoDAO(): ProdutoDAO
}