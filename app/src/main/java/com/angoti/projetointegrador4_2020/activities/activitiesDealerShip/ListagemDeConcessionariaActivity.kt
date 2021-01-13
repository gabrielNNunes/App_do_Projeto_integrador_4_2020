package com.angoti.projetointegrador4_2020.activities.activitiesDealerShip

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.adapters.*
import com.angoti.projetointegrador4_2020.dto.DtoDealership
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListagemDeConcessionariaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        buscaDados()
    }

    private fun buscaDados() {
        RetrofitClient().api.obterConcessionarias("Bearer " + getToken()).enqueue(object : Callback<List<DtoDealership>> {
            override fun onResponse(call: Call<List<DtoDealership>>, response: Response<List<DtoDealership>>) {
                preencheRecyclerView(response.body())
            }

            override fun onFailure(call: Call<List<DtoDealership>>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })

    }

    private fun getToken(): String? {
        val sp = getSharedPreferences("dados", 0)
        val tokenAutenticacao: String? = sp.getString("token", null)
        return tokenAutenticacao
    }

    private fun preencheRecyclerView(lista: List<DtoDealership>?) {
        var mRecyclerView = findViewById<RecyclerView>(R.id.rv_lista)
        val listaAlteravel = lista!!.toMutableList()
        var concessionariaAdapter = ConcessionariaAdapter(listaAlteravel, this)
        mRecyclerView.adapter = concessionariaAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelper = ItemTouchHelper(SwipeConcessionaria(concessionariaAdapter))
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    fun excluirConcessionaria(id: Long) {
        RetrofitClient().api.excluirConcessionaria(id, "Bearer ${getToken()}").enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(this@ListagemDeConcessionariaActivity, "Código de exclusão: ${response.code()}", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}