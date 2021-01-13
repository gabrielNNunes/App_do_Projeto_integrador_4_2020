package com.angoti.projetointegrador4_2020.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.activitiesCategory.AlteracaoDeCategoriaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesCategory.ListagemDeCategoriaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.ListagemDeUsuariosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.AlteracaoDeUsuarioActivity
import com.angoti.projetointegrador4_2020.dto.CategoryDTO

import com.angoti.projetointegrador4_2020.dto.UserDto


class CategoriaAdapter(private val dados: MutableList<CategoryDTO>, val contexto: Context) :
    RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    var mRecentlyDeletdItem: CategoryDTO? = null
    var mRecentlyDeletdItemPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        return CategoriaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_uma_categoria, parent, false))
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.nome.text = dados.get(position).nome

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
        (contexto as ListagemDeCategoriaActivity).excluirCategoria(mRecentlyDeletdItem!!.id)
    }

    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val nome = itemView.findViewById<TextView>(R.id.rv_lista_de_categoria_nome)


        init {
            itemView.setOnClickListener(this);
        }

        override fun onClick(v: View?) {
            val user = dados[layoutPosition]
            val intent = Intent(contexto, AlteracaoDeCategoriaActivity::class.java)
            intent.putExtra("id", user.id)
            intent.putExtra("nome", user.nome)
            contexto.startActivity(intent)
        }
    }

}

