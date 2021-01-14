package com.angoti.projetointegrador4_2020.activities.activitiesProduct

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.adapters.EnderecoUserAdapter
import com.angoti.projetointegrador4_2020.adapters.ProductAdapter
import com.angoti.projetointegrador4_2020.adapters.SwipeEnderecoUser
import com.angoti.projetointegrador4_2020.adapters.SwipeProduto

import com.angoti.projetointegrador4_2020.dto.AddressDTO
import com.angoti.projetointegrador4_2020.dto.ProductDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListagemDeProdutosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        buscaDados()
    }

    private fun buscaDados() {
        RetrofitClient().api.obterProducts().enqueue(object : Callback<List<ProductDTO>> {
            override fun onResponse(call: Call<List<ProductDTO>>, response: Response<List<ProductDTO>>) {
                preencheRecyclerView(response.body())
            }

            override fun onFailure(call: Call<List<ProductDTO>>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })

    }

    private fun getToken(): String? {
        val sp = getSharedPreferences("dados", 0)
        val tokenAutenticacao: String? = sp.getString("token", null)
        return tokenAutenticacao
    }

    private fun preencheRecyclerView(lista: List<ProductDTO>?) {
        var mRecyclerView = findViewById<RecyclerView>(R.id.rv_lista)
        val listaAlteravel = lista!!.toMutableList()
        var produtoAdapter = ProductAdapter(listaAlteravel, this)
        mRecyclerView.adapter = produtoAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelper = ItemTouchHelper(SwipeProduto(produtoAdapter))
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    fun excluirProduto(id: Long) {
        RetrofitClient().api.excluirProduct(id, "Bearer ${getToken()}").enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(this@ListagemDeProdutosActivity, "Código de exclusão: ${response.code()}", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}