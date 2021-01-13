package com.angoti.projetointegrador4_2020.activities.activitiesCategory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.dto.CategoryDTO
import com.angoti.projetointegrador4_2020.dto.UserDto
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlteracaoDeCategoriaActivity : AppCompatActivity() {


    private lateinit var nome: EditText

    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_categoria)


        nome = findViewById<EditText>(R.id.et_cadastro_categoria_nome)


        id = intent.getLongExtra("id", 0)
        val nome = intent.getStringExtra("nome")



        this.nome.setText(nome)

        //val layout = findViewById<ViewGroup>(R.id.cl_cadastro)
        //layout.removeView(findViewById(R.id.et_cadastro_password))
    }

    fun cadastrar(v: View) {
        val dto = CategoryDTO(id, nome.text.toString())
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.alterarCategoria(id, "Bearer $tokenAutorizacao", dto).enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.code() == 200) {
                    Toast.makeText(this@AlteracaoDeCategoriaActivity, "Alteração realizada", Toast.LENGTH_LONG).show();
                    startActivity(Intent(this@AlteracaoDeCategoriaActivity, ListagemDeCategoriaActivity::class.java))
                }else Toast.makeText(this@AlteracaoDeCategoriaActivity, "Falha", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}