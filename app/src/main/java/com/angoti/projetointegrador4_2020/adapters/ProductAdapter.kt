package com.angoti.projetointegrador4_2020.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.activitiesCategory.AlteracaoDeCategoriaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesCategory.ListagemDeCategoriaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesProduct.AlteracaoDeProdutosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesProduct.ListagemDeProdutosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.ListagemDeUsuariosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.AlteracaoDeUsuarioActivity
import com.angoti.projetointegrador4_2020.dto.CategoryDTO
import com.angoti.projetointegrador4_2020.dto.ProductDTO

import com.angoti.projetointegrador4_2020.dto.UserDto
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAdapter(private val dados: MutableList<ProductDTO>, val contexto: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var mRecentlyDeletdItem: ProductDTO? = null
    var mRecentlyDeletdItemPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_um_produto, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.cor.text = dados.get(position).cor
        holder.modelo.text = dados.get(position).modelo
        holder.descriction.text = dados.get(position).description
        holder.precoDiaria.text = "R$"+dados.get(position).precoDiaria.toString()+",00"
        holder.statusveiculo.text = dados.get(position).statusveiculo


    }

    override fun getItemCount() = dados.size

    //swipe
    fun deleteItem(position: Int) {
        mRecentlyDeletdItem = dados[position]
        mRecentlyDeletdItemPosition = position
        dados.removeAt(position)
        notifyItemRemoved(position)
        showAlertDialog()
    }

    //swipe
    private fun showAlertDialog() {
        val builderDialog = AlertDialog.Builder(contexto)
        builderDialog.setTitle("Exclusão de categoria")
        builderDialog.setMessage("Confirma?")
        builderDialog.setPositiveButton("sim", DialogInterface.OnClickListener({ dialog, which -> excluir() }))
        builderDialog.setNegativeButton("não", DialogInterface.OnClickListener({ dialog, which -> undoDelete() }))
        builderDialog.create().show()
    }

    //swipe
    private fun undoDelete() {
        dados.add(mRecentlyDeletdItemPosition, mRecentlyDeletdItem!!)
        notifyItemInserted(mRecentlyDeletdItemPosition)
    }

    //swipe
    private fun excluir() {
        (contexto as ListagemDeProdutosActivity).excluirProduto(mRecentlyDeletdItem!!.id)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val cor = itemView.findViewById<TextView>(R.id.rv_lista_de_produto_cor)
        val descriction = itemView.findViewById<TextView>(R.id.rv_lista_de_produto_descricao)
        val modelo = itemView.findViewById<TextView>(R.id.rv_lista_de_produto_modelo)
        val precoDiaria = itemView.findViewById<TextView>(R.id.rv_lista_de_produto_preco)
        val statusveiculo = itemView.findViewById<TextView>(R.id.rv_lista_de_produto_status)


        init {
            itemView.setOnClickListener(this);
        }

        override fun onClick(v: View?) {
            val produto = dados[layoutPosition]
            val intent = Intent(contexto, AlteracaoDeProdutosActivity::class.java)
            intent.putExtra("id", produto.id)
            intent.putExtra("cor", produto.cor)
            intent.putExtra("description", produto.description)
            intent.putExtra("imgUrl", produto.imgUrl)
            intent.putExtra("modelo", produto.modelo)
            intent.putExtra("placa", produto.placa)
            intent.putExtra("precoDiaria", produto.precoDiaria)
            intent.putExtra("quilometragem", produto.quilometragem)
            intent.putExtra("statusveiculo ", produto.statusveiculo)

                contexto.startActivity(intent)
        }
    }

}

