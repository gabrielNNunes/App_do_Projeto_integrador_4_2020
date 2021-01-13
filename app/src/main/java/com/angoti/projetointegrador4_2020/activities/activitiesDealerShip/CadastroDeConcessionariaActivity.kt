package com.angoti.projetointegrador4_2020.activities.activitiesDealerShip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.MainActivity
import com.angoti.projetointegrador4_2020.dto.DtoDealership
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDeConcessionariaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_concessionaria)
    }

    fun cadastrar(view: View) {



        val name = findViewById<EditText>(R.id.et_cadastro_concessionaria_nome).text.toString()
        val telefone = findViewById<EditText>(R.id.et_cadastro_concessionaria_telefone).text.toString()


        val dealership = DtoDealership(0,name,telefone)
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.cadastraConcessionaria(dealership,"Bearer $tokenAutorizacao").enqueue(object : Callback<DtoDealership> {
            override fun onResponse(call: Call<DtoDealership>, response: Response<DtoDealership>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@CadastroDeConcessionariaActivity, "Endereço cadastrado com id = " + response.body()?.id, Toast.LENGTH_LONG)
                        .show();
                    startActivity(Intent(this@CadastroDeConcessionariaActivity, MainActivity::class.java))
                }else {
                    val mensagem = JSONObject(response.errorBody()?.string())
                    Toast.makeText(this@CadastroDeConcessionariaActivity, "Não cadastrado. Erro: ${mensagem.getString("errors")}", Toast.LENGTH_LONG)
                        .show();
                    Log.e("erro", "Erro: ${mensagem.getString("errors")}")
                }
            }

            override fun onFailure(call: Call<DtoDealership>, t: Throwable) {
                Toast.makeText(this@CadastroDeConcessionariaActivity, "Erro: " + t.message, Toast.LENGTH_LONG)
                    .show();
                Log.e("erro", t.toString())

            }
        })
    }
}