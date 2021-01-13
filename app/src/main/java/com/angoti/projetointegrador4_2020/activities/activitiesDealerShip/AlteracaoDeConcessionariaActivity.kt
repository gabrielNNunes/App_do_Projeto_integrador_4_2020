package com.angoti.projetointegrador4_2020.activities.activitiesDealerShip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.dto.DtoDealership
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlteracaoDeConcessionariaActivity : AppCompatActivity() {


    private lateinit var name: EditText
    private lateinit var telefone: EditText
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_concessionaria)

        name = findViewById<EditText>(R.id.et_cadastro_concessionaria_nome)
        telefone = findViewById<EditText>(R.id.et_cadastro_concessionaria_telefone)

        id = intent.getLongExtra("id", 0)
        val name = intent.getStringExtra("name")
        val telefone = intent.getStringExtra("telefone")


        this.name.setText(name)
        this.telefone.setText(telefone)

        //findViewById<EditText>(R.id.et_cadastro_password).visibility = View.GONE
        //val layout = findViewById<ViewGroup>(R.id.cl_cadastro)
        //layout.removeView(findViewById(R.id.et_cadastro_password))
    }

    fun cadastrar(v: View) {
        val dealership: DtoDealership = DtoDealership(id,name.text.toString(),telefone.text.toString())
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.alterarConcessionaria(id, "Bearer $tokenAutorizacao", dealership).enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.code() == 200) {
                    Toast.makeText(this@AlteracaoDeConcessionariaActivity, "Alteração realizada", Toast.LENGTH_LONG).show();
                    startActivity(Intent(this@AlteracaoDeConcessionariaActivity, ListagemDeConcessionariaActivity::class.java))
                }else Toast.makeText(this@AlteracaoDeConcessionariaActivity, "Falha", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}