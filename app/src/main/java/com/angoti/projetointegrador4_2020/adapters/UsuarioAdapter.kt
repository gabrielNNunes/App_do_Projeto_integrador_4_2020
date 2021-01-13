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
import com.angoti.projetointegrador4_2020.activities.activitiesUser.ListagemDeUsuariosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.AlteracaoDeUsuarioActivity

import com.angoti.projetointegrador4_2020.dto.UserDto


class UsuarioAdapter(private val dados: MutableList<UserDto>, val contexto: Context) :
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    var mRecentlyDeletdItem: UserDto? = null
    var mRecentlyDeletdItemPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        return UsuarioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_um_usuario, parent, false))
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.nome.text = dados.get(position).name
        holder.email.text = dados.get(position).email
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
        builderDialog.setTitle("Exclusão de usuário")
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
        (contexto as ListagemDeUsuariosActivity).excluirUsuario(mRecentlyDeletdItem!!.id)
    }

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val nome = itemView.findViewById<TextView>(R.id.rv_lista_de_usuarios_nome)
        val email = itemView.findViewById<TextView>(R.id.rv_lista_de_usuarios_email)

        init {
            itemView.setOnClickListener(this);
        }

        override fun onClick(v: View?) {
            val user = dados[layoutPosition]
            val intent = Intent(contexto, AlteracaoDeUsuarioActivity::class.java)
            intent.putExtra("id", user.id)
            intent.putExtra("nome", user.name)
            intent.putExtra("email", user.email)
            intent.putExtra("cel", user.phone)
            intent.putExtra("cpfCnpj", user.cpfCnpj)
            intent.putExtra("cnh", user.cnh)
            intent.putExtra("dataNascimento", user.dataNascimento)
            contexto.startActivity(intent)
        }
    }

}

