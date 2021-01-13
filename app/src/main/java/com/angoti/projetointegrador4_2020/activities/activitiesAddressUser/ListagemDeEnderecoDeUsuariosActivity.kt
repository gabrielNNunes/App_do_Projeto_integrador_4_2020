package com.angoti.projetointegrador4_2020.activities.activitiesAddressUser

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.adapters.EnderecoUserAdapter
import com.angoti.projetointegrador4_2020.adapters.SwipeEnderecoUser

import com.angoti.projetointegrador4_2020.dto.AddressDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListagemDeEnderecoDeUsuariosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        buscaDados()
    }

    private fun buscaDados() {
        RetrofitClient().api.obterEnderecoUsuarios("Bearer " + getToken()).enqueue(object : Callback<List<AddressDTO>> {
            override fun onResponse(call: Call<List<AddressDTO>>, response: Response<List<AddressDTO>>) {
                preencheRecyclerView(response.body())
            }

            override fun onFailure(call: Call<List<AddressDTO>>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })

    }

    private fun getToken(): String? {
        val sp = getSharedPreferences("dados", 0)
        val tokenAutenticacao: String? = sp.getString("token", null)
        return tokenAutenticacao
    }

    private fun preencheRecyclerView(listaDeEnderecos: List<AddressDTO>?) {
        var mRecyclerView = findViewById<RecyclerView>(R.id.rv_lista)
        val listaAlteravel = listaDeEnderecos!!.toMutableList()
        var enderecoAdapter = EnderecoUserAdapter(listaAlteravel, this)
        mRecyclerView.adapter = enderecoAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelper = ItemTouchHelper(SwipeEnderecoUser(enderecoAdapter))
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    fun excluirEnderecoUsuario(id: Long) {
        RetrofitClient().api.excluirEnderecoUsuario(id, "Bearer ${getToken()}").enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(this@ListagemDeEnderecoDeUsuariosActivity, "Código de exclusão: ${response.code()}", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}