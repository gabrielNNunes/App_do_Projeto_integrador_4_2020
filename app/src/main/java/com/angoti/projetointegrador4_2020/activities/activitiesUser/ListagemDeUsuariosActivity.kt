package com.angoti.projetointegrador4_2020.activities.activitiesUser

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.adapters.Swipe
import com.angoti.projetointegrador4_2020.adapters.UsuarioAdapter
import com.angoti.projetointegrador4_2020.dto.UserDto
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListagemDeUsuariosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        buscaDados()
    }

    private fun buscaDados() {
        RetrofitClient().api.obterUsuarios("Bearer " + getToken()).enqueue(object : Callback<List<UserDto>> {
            override fun onResponse(call: Call<List<UserDto>>, response: Response<List<UserDto>>) {
                preencheRecyclerView(response.body())
            }

            override fun onFailure(call: Call<List<UserDto>>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })

    }

    private fun getToken(): String? {
        val sp = getSharedPreferences("dados", 0)
        val tokenAutenticacao: String? = sp.getString("token", null)
        return tokenAutenticacao
    }

    private fun preencheRecyclerView(listaDeUsuarios: List<UserDto>?) {
        var mRecyclerView = findViewById<RecyclerView>(R.id.rv_lista)
        val listaAlteravel = listaDeUsuarios!!.toMutableList()
        var usuarioAdapter = UsuarioAdapter(listaAlteravel, this)
        mRecyclerView.adapter = usuarioAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelper = ItemTouchHelper(Swipe(usuarioAdapter))
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    fun excluirUsuario(id: Long) {
        RetrofitClient().api.excluirUsuario(id, "Bearer ${getToken()}").enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(this@ListagemDeUsuariosActivity, "Código de exclusão: ${response.code()}", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}