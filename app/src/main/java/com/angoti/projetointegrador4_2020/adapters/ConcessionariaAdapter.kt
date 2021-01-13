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
import com.angoti.projetointegrador4_2020.activities.activitiesDealerShip.AlteracaoDeConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesDealerShip.ListagemDeConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.ListagemDeUsuariosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.AlteracaoDeUsuarioActivity
import com.angoti.projetointegrador4_2020.dto.DtoDealership
import com.angoti.projetointegrador4_2020.dto.UserDto


class ConcessionariaAdapter(private val dados: MutableList<DtoDealership>, val contexto: Context) :
    RecyclerView.Adapter<ConcessionariaAdapter.ConcessionariaViewHolder>() {

    var mRecentlyDeletdItem: DtoDealership? = null
    var mRecentlyDeletdItemPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcessionariaViewHolder {
        return ConcessionariaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_uma_concessionaria, parent, false))
    }

    override fun onBindViewHolder(holder: ConcessionariaViewHolder, position: Int) {
        holder.name.text = dados.get(position).name
        holder.telefone.text = dados.get(position).telefone
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
        builderDialog.setTitle("Exclusão de Concessionária")
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
        (contexto as ListagemDeConcessionariaActivity).excluirConcessionaria(mRecentlyDeletdItem!!.id)
    }

    inner class ConcessionariaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val name = itemView.findViewById<TextView>(R.id.rv_lista_de_concessionaria_nome)
        val telefone = itemView.findViewById<TextView>(R.id.rv_lista_de_concessionaria_telefone)

        init {
            itemView.setOnClickListener(this);
        }

        override fun onClick(v: View?) {
            val user = dados[layoutPosition]
            val intent = Intent(contexto, AlteracaoDeConcessionariaActivity::class.java)
            intent.putExtra("id", user.id)
            intent.putExtra("name", user.name)
            intent.putExtra("telefone", user.telefone)
            contexto.startActivity(intent)
        }
    }

}

