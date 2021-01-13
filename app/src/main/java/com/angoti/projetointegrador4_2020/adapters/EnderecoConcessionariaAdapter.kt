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
import com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership.AlteracaoDeEnderecoDaConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership.ListagemDeEnderecoDaConcessionariaActivity
import com.angoti.projetointegrador4_2020.dto.AddressDTO


class EnderecoConcessionariaAdapter(private val dados: MutableList<AddressDTO>, val contexto: Context) :
    RecyclerView.Adapter<EnderecoConcessionariaAdapter.EnderecoViewHolder>() {

    var mRecentlyDeletdItem: AddressDTO? = null
    var mRecentlyDeletdItemPosition: Int = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnderecoViewHolder {
        return EnderecoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_um_endereco, parent, false))
    }

    override fun onBindViewHolder(holder: EnderecoViewHolder, position: Int) {
        holder.cep.text = dados.get(position).cep
        holder.cidade.text = dados.get(position).cidade
        holder.estado.text = dados.get(position).estado
        holder.logradouro.text = dados.get(position).logradouro
        holder.numero.text = dados.get(position).numero

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
        builderDialog.setTitle("Exclusão de Endereço")
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
        (contexto as ListagemDeEnderecoDaConcessionariaActivity).excluirEnderecoConcessionaria(mRecentlyDeletdItem!!.id)
    }

    inner class EnderecoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val cep = itemView.findViewById<TextView>(R.id.rv_lista_de_endereco_cep)
        val cidade = itemView.findViewById<TextView>(R.id.rv_lista_de_endereco_cidade)
        val estado = itemView.findViewById<TextView>(R.id.rv_lista_de_endereco_estado)
        val logradouro = itemView.findViewById<TextView>(R.id.rv_lista_de_endereco_logradouro)
        val numero = itemView.findViewById<TextView>(R.id.rv_lista_de_endereco_numero)

        init {
            itemView.setOnClickListener(this);
        }
        /*
            id
            bairro
            cep
            cidade
            estado
            logradouro
            numero
            referencia
            */

        override fun onClick(v: View?) {
            val addressUser = dados[layoutPosition]
            val intent = Intent(contexto, AlteracaoDeEnderecoDaConcessionariaActivity::class.java)
            intent.putExtra("id", addressUser.id)
            intent.putExtra("bairro", addressUser.bairro)
            intent.putExtra("cep", addressUser.cep)
            intent.putExtra("cidade", addressUser.cidade)
            intent.putExtra("estado", addressUser.estado)
            intent.putExtra("logradouro", addressUser.logradouro)
            intent.putExtra("numero", addressUser.numero)
            intent.putExtra("referencia", addressUser.referencia)
            contexto.startActivity(intent)
        }
    }

}

