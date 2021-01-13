package com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.MainActivity
import com.angoti.projetointegrador4_2020.dto.AddressDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDeEnderecoDaConcessionariaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_endereco)
        findViewById<EditText>(R.id.et_cadastro_endereco_id).hint = "Id da Concessionária"
    }

    fun cadastrar(view: View) {

        val idAddress = findViewById<EditText>(R.id.et_cadastro_endereco_id)
        val bairro = findViewById<EditText>(R.id.et_cadastro_endereco_bairro).text.toString()
        val cep = findViewById<EditText>(R.id.et_cadastro_endereco_cep).text.toString()
        val cidade = findViewById<EditText>(R.id.et_cadastro_endereco_cidade).text.toString()
        val estado = findViewById<EditText>(R.id.et_cadastro_endereco_estado).text.toString()
        val logradouro = findViewById<EditText>(R.id.et_cadastro_endereco_logradouro).text.toString()
        val numero = findViewById<EditText>(R.id.et_cadastro_endereco_numero).text.toString()
        val referencia = findViewById<EditText>(R.id.et_cadastro_endereco_referencia).text.toString()

        val address = AddressDTO(0,bairro, cep, cidade, estado, logradouro, numero, referencia)
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.cadastraEnderecoConcessionaria(idAddress.text.toString().toLong(), "Bearer $tokenAutorizacao", address).enqueue(object : Callback<AddressDTO> {
            override fun onResponse(call: Call<AddressDTO>, response: Response<AddressDTO>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@CadastroDeEnderecoDaConcessionariaActivity, "Endereço cadastrado com id = " + response.body()?.id, Toast.LENGTH_LONG)
                        .show();
                    startActivity(Intent(this@CadastroDeEnderecoDaConcessionariaActivity, MainActivity::class.java))
                }else {
                    val mensagem = JSONObject(response.errorBody()?.string())
                    Toast.makeText(this@CadastroDeEnderecoDaConcessionariaActivity, "Não cadastrado. Erro: ${mensagem.getString("errors")}", Toast.LENGTH_LONG)
                        .show();
                    Log.e("erro", "Erro: ${mensagem.getString("errors")}")
                }
            }

            override fun onFailure(call: Call<AddressDTO>, t: Throwable) {
                Toast.makeText(this@CadastroDeEnderecoDaConcessionariaActivity, "Erro: " + t.message, Toast.LENGTH_LONG)
                    .show();
                Log.e("erro", t.toString())

            }
        })
    }
}