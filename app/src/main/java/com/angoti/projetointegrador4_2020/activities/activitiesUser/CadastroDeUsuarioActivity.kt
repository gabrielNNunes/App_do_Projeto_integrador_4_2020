package com.angoti.projetointegrador4_2020.activities.activitiesUser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.MainActivity
import com.angoti.projetointegrador4_2020.dto.UserDto
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDeUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_usuario)
    }

    fun cadastrar(view: View) {
        val email = findViewById<EditText>(R.id.et_cadastro_usuario_email).text.toString()
        val nome = findViewById<EditText>(R.id.et_cadastro_usuario_nome).text.toString()
        val senha = findViewById<EditText>(R.id.et_cadastro_usuario_senha).text.toString()
        val cel = findViewById<EditText>(R.id.et_cadastro_usuario_telefone).text.toString()
        val cpfCnpj = findViewById<EditText>(R.id.et_cadastro_usuario_cpfCnpj).text.toString()
        val cnh = findViewById<EditText>(R.id.et_cadastro_usuario_cnh).text.toString()
        val dataNascimento = findViewById<EditText>(R.id.et_cadastro_usuario_dataNascimento).text.toString()
        val user = UserDto(email, 0, nome, senha, cel,cpfCnpj, cnh, dataNascimento)

        RetrofitClient().api.cadastraUsuario(user).enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@CadastroDeUsuarioActivity, "Usuário cadastrado com id = " + response.body()?.id, Toast.LENGTH_LONG)
                        .show();
                    startActivity(Intent(this@CadastroDeUsuarioActivity, MainActivity::class.java))
                }else {
                    val mensagem = JSONObject(response.errorBody()?.string())
                    Toast.makeText(this@CadastroDeUsuarioActivity, "Não cadastrado. Erro: ${mensagem.getString("errors")}", Toast.LENGTH_LONG)
                        .show();
                    Log.e("erro", "Erro: ${mensagem.getString("errors")}")
                }
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                Toast.makeText(this@CadastroDeUsuarioActivity, "Erro: " + t.message, Toast.LENGTH_LONG)
                    .show();
                Log.e("erro", t.toString())

            }
        })
    }
}