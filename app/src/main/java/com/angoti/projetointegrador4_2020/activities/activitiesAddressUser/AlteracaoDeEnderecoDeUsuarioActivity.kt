package com.angoti.projetointegrador4_2020.activities.activitiesAddressUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership.ListagemDeEnderecoDaConcessionariaActivity
import com.angoti.projetointegrador4_2020.dto.AddressDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlteracaoDeEnderecoDeUsuarioActivity : AppCompatActivity() {

/*
            id
            bairro
            cep
            cidade
            estado
            logradouro
            numero
            referencia
            */
    private lateinit var idUsuario:EditText
    private lateinit var bairro: EditText
    private lateinit var cep: EditText
    private lateinit var cidade: EditText
    private lateinit var estado: EditText
    private lateinit var logradouro: EditText
    private lateinit var numero: EditText
    private lateinit var referencia: EditText
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_endereco)


        idUsuario = findViewById<EditText>(R.id.et_cadastro_endereco_id)
        idUsuario.hint = "Id do Usuário"
        idUsuario.isEnabled = false

        bairro = findViewById<EditText>(R.id.et_cadastro_endereco_bairro)
        cep = findViewById<EditText>(R.id.et_cadastro_endereco_cep)
        cidade = findViewById<EditText>(R.id.et_cadastro_endereco_cidade)
        estado = findViewById<EditText>(R.id.et_cadastro_endereco_estado)
        logradouro = findViewById<EditText>(R.id.et_cadastro_endereco_logradouro)
        numero = findViewById<EditText>(R.id.et_cadastro_endereco_numero)
        referencia = findViewById<EditText>(R.id.et_cadastro_endereco_referencia)

        id = intent.getLongExtra("id", 0)
        val bairro = intent.getStringExtra("bairro")
        val cep = intent.getStringExtra("cep")
        val cidade = intent.getStringExtra("cidade")
        val estado = intent.getStringExtra("estado")
        val logradouro = intent.getStringExtra("logradouro")
        val numero = intent.getStringExtra("numero")
        val referencia = intent.getStringExtra("referencia")

        this.bairro.setText(bairro)
        this.cep.setText(cep)
        this.cidade.setText(cidade)
        this.estado.setText(estado)
        this.logradouro.setText(logradouro)
        this.numero.setText(numero)
        this.referencia.setText(referencia)

        //findViewById<EditText>(R.id.et_cadastro_password).visibility = View.GONE
        //val layout = findViewById<ViewGroup>(R.id.cl_cadastro)
        //layout.removeView(findViewById(R.id.et_cadastro_password))
    }

    fun cadastrar(v: View) {
        val address_DTO: AddressDTO = AddressDTO(id,bairro.text.toString(),cep.text.toString(),cidade.text.toString(),estado.text.toString(),logradouro.text.toString(),numero.text.toString(),referencia.text.toString())
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.alterarEnderecoUsuario(id, "Bearer $tokenAutorizacao", address_DTO).enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.code() == 200) {
                    Toast.makeText(this@AlteracaoDeEnderecoDeUsuarioActivity, "Alteração realizada", Toast.LENGTH_LONG).show();
                    startActivity(Intent(this@AlteracaoDeEnderecoDeUsuarioActivity, ListagemDeEnderecoDeUsuariosActivity::class.java))
                }else Toast.makeText(this@AlteracaoDeEnderecoDeUsuarioActivity, "Falha", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}