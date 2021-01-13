package com.angoti.projetointegrador4_2020.activities.activitiesCategory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.MainActivity
import com.angoti.projetointegrador4_2020.dto.CategoryDTO
import com.angoti.projetointegrador4_2020.dto.UserDto
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDeCategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_categoria)
    }

    fun cadastrar(view: View) {

        val nome = findViewById<EditText>(R.id.et_cadastro_categoria_nome).text.toString()

        val categoria = CategoryDTO(0,nome)

        RetrofitClient().api.cadastraCategoria(categoria).enqueue(object : Callback<CategoryDTO> {
            override fun onResponse(call: Call<CategoryDTO>, response: Response<CategoryDTO>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@CadastroDeCategoriaActivity, "Categoria cadastrada com id = " + response.body()?.id, Toast.LENGTH_LONG)
                        .show();
                    startActivity(Intent(this@CadastroDeCategoriaActivity, MainActivity::class.java))
                }else {
                    val mensagem = JSONObject(response.errorBody()?.string())
                    Toast.makeText(this@CadastroDeCategoriaActivity, "Não cadastrado. Erro: ${mensagem.getString("errors")}", Toast.LENGTH_LONG)
                        .show();
                    Log.e("erro", "Erro: ${mensagem.getString("errors")}")
                }
            }

            override fun onFailure(call: Call<CategoryDTO>, t: Throwable) {
                Toast.makeText(this@CadastroDeCategoriaActivity, "Erro: " + t.message, Toast.LENGTH_LONG)
                    .show();
                Log.e("erro", t.toString())

            }
        })
    }
}