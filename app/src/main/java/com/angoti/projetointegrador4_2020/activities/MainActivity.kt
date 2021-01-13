package com.angoti.projetointegrador4_2020.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership.CadastroDeEnderecoDaConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership.ListagemDeEnderecoDaConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesAddressUser.CadastroDeEderecoDeUsuarioActivity
import com.angoti.projetointegrador4_2020.activities.activitiesAddressUser.ListagemDeEnderecoDeUsuariosActivity
import com.angoti.projetointegrador4_2020.activities.activitiesAuth.LoginActivity
import com.angoti.projetointegrador4_2020.activities.activitiesCategory.CadastroDeCategoriaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesCategory.ListagemDeCategoriaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesDealerShip.CadastroDeConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesDealerShip.ListagemDeConcessionariaActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.AlteracaoDeUsuarioActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.CadastroDeUsuarioActivity
import com.angoti.projetointegrador4_2020.activities.activitiesUser.ListagemDeUsuariosActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_alterar_user -> {
                startActivity(Intent(this, AlteracaoDeUsuarioActivity::class.java))
                true
            }
            R.id.action_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            R.id.action_novo_user -> {
                startActivity(Intent(this, CadastroDeUsuarioActivity::class.java))
                true
            }
            R.id.action_todos_user -> {
                startActivity(Intent(this, ListagemDeUsuariosActivity::class.java))
                true
            }
           R.id.action_novo_enredeco_user -> {
                startActivity(Intent(this, CadastroDeEderecoDeUsuarioActivity::class.java))
                true
            }
             R.id.action_todos_enredeco_user -> {
                startActivity(Intent(this, ListagemDeEnderecoDeUsuariosActivity::class.java))
                true
            }
            R.id.action_nova_concessionaria -> {
                startActivity(Intent(this, CadastroDeConcessionariaActivity::class.java))
                true
            }
            R.id.action_todas_concessionarias-> {
                startActivity(Intent(this, ListagemDeConcessionariaActivity::class.java))
                true
            }
            R.id.action_novo_endereco_concessionaria -> {
                startActivity(Intent(this, CadastroDeEnderecoDaConcessionariaActivity::class.java))
                true
            }
            R.id.action_todos_enderecos_concessionaria-> {
                startActivity(Intent(this, ListagemDeEnderecoDaConcessionariaActivity::class.java))
                true
            }
            R.id.action_nova_categoria -> {
                startActivity(Intent(this, CadastroDeCategoriaActivity::class.java))
                true
            }
            R.id.action_todas_categorias-> {
                startActivity(Intent(this, ListagemDeCategoriaActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}