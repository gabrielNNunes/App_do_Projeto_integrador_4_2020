package com.angoti.projetointegrador4_2020.activities.activitiesProduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.MainActivity
import com.angoti.projetointegrador4_2020.dto.CategoryDTO
import com.angoti.projetointegrador4_2020.dto.ProductDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastroDeProdutosActivity : AppCompatActivity() {
    //var listaCategoriasSelecionadas: MutableList<CategoryDTO> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_produto)
        /*val layout = findViewById<LinearLayout>(R.id.layout_buttons)


        RetrofitClient().api.obterCategorias().enqueue(object : Callback<List<CategoryDTO>> {
            override fun onResponse(call: Call<List<CategoryDTO>>, response: Response<List<CategoryDTO>>) {
                val lista = response.body()
                if (lista != null) {
                    lista.forEach {
                        val cb = CheckBox(applicationContext)
                        cb.text = it.nome
                        var categoria = it
                        //cb.isChecked
                        layout.addView(cb)
                        cb.setOnClickListener {
                            clickCheckBox(categoria)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<CategoryDTO>>, t: Throwable) {
                Log.e("erro", t.message)
            }

        })*/
    }

       /* fun clickCheckBox(categoria:CategoryDTO){
            var s=""
            if(listaCategoriasSelecionadas.contains(categoria)){
                listaCategoriasSelecionadas.remove(categoria)
                s ="Categoria "+  categoria.nome + " removida!"
            }else{
                listaCategoriasSelecionadas.add(categoria)
                s ="Categoria "+ categoria.nome + " adicionada!"
            }

            Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
        }*/


        fun cadastrar(view: View) {



            val cor: String = findViewById<EditText>(R.id.et_cadastro_produto_cor).text.toString()
            val description = findViewById<EditText>(R.id.et_cadastro_produto_descricao).text.toString()
            val imgUrl = findViewById<EditText>(R.id.et_cadastro_produto_urlFoto).text.toString()
            val modelo = findViewById<EditText>(R.id.et_cadastro_produto_modelo).text.toString()
            val placa = findViewById<EditText>(R.id.et_cadastro_produto_placa).text.toString()
            val precoDiaria = findViewById<EditText>(R.id.et_cadastro_produto_preco).text.toString()
            val quilometragem = findViewById<EditText>(R.id.et_cadastro_produto_quilometragem).text.toString()
            val statusveiculo = findViewById<EditText>(R.id.et_cadastro_produto_status).text.toString()






             //val listaCategorias:List<CategoryDTO> = listaCategoriasSelecionadas

        val produto = ProductDTO(0,cor,description,imgUrl,modelo,placa,precoDiaria.toInt(),quilometragem.toInt(),statusveiculo)
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.cadastraProduct("Bearer $tokenAutorizacao", produto).enqueue(object : Callback<ProductDTO> {
            override fun onResponse(call: Call<ProductDTO>, response: Response<ProductDTO>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@CadastroDeProdutosActivity, "Endereço cadastrado com id = " + response.body()?.id, Toast.LENGTH_LONG)
                        .show();
                    startActivity(Intent(this@CadastroDeProdutosActivity, MainActivity::class.java))
                }else {
                    val mensagem = JSONObject(response.errorBody()?.string())
                    Toast.makeText(this@CadastroDeProdutosActivity, "Não cadastrado. Erro: ${mensagem.getString("errors")}", Toast.LENGTH_LONG)
                        .show();
                    Log.e("erro", "Erro: ${mensagem.getString("errors")}")
                }
            }

            override fun onFailure(call: Call<ProductDTO>, t: Throwable) {
                Toast.makeText(this@CadastroDeProdutosActivity, "Erro: " + t.message, Toast.LENGTH_LONG)
                    .show();
                Log.e("erro", t.toString())

            }
        })

        }

    }