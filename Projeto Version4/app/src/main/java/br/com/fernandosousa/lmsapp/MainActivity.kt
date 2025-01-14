package br.com.fernandosousa.lmsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        campo_imagem.setImageResource(R.drawable.bonaparte)

        texto_login.text = getString(R.string.mensagem_login)

        // evento no botao de login forma 1
//        botao_login.setOnClickListener {
//            val valorUsuario = campo_usuario.text.toString()
//            val valorSenha = campo_senha.text.toString()
//            Toast.makeText(this, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()
//        }
        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            checkLembrar.isChecked = lembrar
        }

        // segunda forma: delegar para método
        botao_login.setOnClickListener {onClickLogin() }

    }

    fun onClickLogin(){
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()
// armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkLembrar.isChecked)
// verificar se é para lembrar nome e senha
        if (checkLembrar.isChecked) {
            Prefs.setString("lembrarNome", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
        } else{
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        if (valorUsuario == "aluno" ) {

            if (valorSenha == "impacta") {
                // criar intent
                val intent = Intent(context, TelaInicialActivity::class.java)
                // colocar parâmetros (opcional)
                val params = Bundle()
                params.putString("nome", "Fernando Sousa")
                intent.putExtras(params)

                // enviar parâmetros simplificado
                intent.putExtra("numero", 10)

                // fazer a chamada
                //startActivity(intent)

                // fazer a chamada esperando resultado
                startActivityForResult(intent, 1)
            }
            else{
                Toast.makeText(this, "Login ou Senha Inválido", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this, "Login ou Senha Inválido", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}
