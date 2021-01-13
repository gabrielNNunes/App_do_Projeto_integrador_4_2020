package com.angoti.projetointegrador4_2020.activities.activitiesUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.dto.UserDto
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlteracaoDeUsuarioActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var cpfCnpj: EditText
    private lateinit var cnh: EditText
    private lateinit var dataNascimento: EditText
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_usuario)

        email = findViewById<EditText>(R.id.et_cadastro_usuario_email)
        name = findViewById<EditText>(R.id.et_cadastro_usuario_nome)
        phone = findViewById<EditText>(R.id.et_cadastro_usuario_telefone)
        cpfCnpj = findViewById<EditText>(R.id.et_cadastro_usuario_cpfCnpj)
        cnh = findViewById<EditText>(R.id.et_cadastro_usuario_cnh)
        dataNascimento = findViewById<EditText>(R.id.et_cadastro_usuario_dataNascimento)

        id = intent.getLongExtra("id", 0)
        val nome = intent.getStringExtra("nome")
        val email = intent.getStringExtra("email")
        val cel = intent.getStringExtra("cel")
        val cpfCnpj = intent.getStringExtra("cpfCnpj")
        val cnh = intent.getStringExtra("cnh")
        val dataNascimento = intent.getStringExtra("dataNascimento")

        this.email.setText(email)
        this.name.setText(nome)
        this.phone.setText(cel)
        this.cpfCnpj.setText(cpfCnpj)
        this.cnh.setText(cnh)
        this.dataNascimento.setText(dataNascimento)

        findViewById<EditText>(R.id.et_cadastro_usuario_senha).visibility = View.GONE
        //val layout = findViewById<ViewGroup>(R.id.cl_cadastro)
        //layout.removeView(findViewById(R.id.et_cadastro_password))
    }

    fun cadastrar(v: View) {
        val dtoUser = UserDto(email.text.toString(), id, name.text.toString(), "", phone.text.toString(),cpfCnpj.text.toString(),cnh.text.toString(),dataNascimento.text.toString())
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.alterarUsuario(id, "Bearer $tokenAutorizacao", dtoUser).enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.code() == 200) {
                    Toast.makeText(this@AlteracaoDeUsuarioActivity, "Alteração realizada", Toast.LENGTH_LONG).show();
                    startActivity(Intent(this@AlteracaoDeUsuarioActivity, ListagemDeUsuariosActivity::class.java))
                }else Toast.makeText(this@AlteracaoDeUsuarioActivity, "Falha", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}