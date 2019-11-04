package br.com.fernandosousa.lmsapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_produto.*

class AddProdutoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_produto)

        bt_salvar.setOnClickListener{

            var produto = Produto()
            produto.descricao = campo_descricao.text.toString()
            produto.categoria = campo_categoria.text.toString()
            produto.foto = campo_foto.text.toString()
            produto.quantidade = campo_quantidade.text.toString()

            Thread{
                ProdutoService.save(produto)
                runOnUiThread{
                    finish()
                }
            }.start()
        }


        bt_cancelar.setOnClickListener{

            var intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }
    }
}

