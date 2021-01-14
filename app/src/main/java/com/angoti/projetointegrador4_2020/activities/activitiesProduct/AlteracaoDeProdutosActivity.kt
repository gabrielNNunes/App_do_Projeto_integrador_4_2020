package com.angoti.projetointegrador4_2020.activities.activitiesProduct

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.activitiesAddressDealership.ListagemDeEnderecoDaConcessionariaActivity
import com.angoti.projetointegrador4_2020.dto.AddressDTO
import com.angoti.projetointegrador4_2020.dto.CategoryDTO
import com.angoti.projetointegrador4_2020.dto.ProductDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.lang.Thread

class AlteracaoDeProdutosActivity : AppCompatActivity() {



    private lateinit var cor: EditText
    private lateinit var description: EditText
    private lateinit var imgUrl: EditText
    private lateinit var modelo: EditText
    private lateinit var placa: EditText
    private lateinit var precoDiaria: EditText
    private lateinit var quilometragem: EditText
    private lateinit var statusveiculo: EditText

    private var id: Long = 0
    //var handler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_produto)


        cor = findViewById<EditText>(R.id.et_cadastro_produto_cor)
        description = findViewById<EditText>(R.id.et_cadastro_produto_descricao)
        imgUrl = findViewById<EditText>(R.id.et_cadastro_produto_urlFoto)
        modelo = findViewById<EditText>(R.id.et_cadastro_produto_modelo)
        placa = findViewById<EditText>(R.id.et_cadastro_produto_placa)
        precoDiaria = findViewById<EditText>(R.id.et_cadastro_produto_preco)
        quilometragem = findViewById<EditText>(R.id.et_cadastro_produto_quilometragem)
        statusveiculo = findViewById<EditText>(R.id.et_cadastro_produto_status)
        //val fotoProduct = findViewById<ImageView>(R.id.iv_lista_de_produto_foto)

            id = intent.getLongExtra("id", 0)
        val cor = intent.getStringExtra("cor")
        val description = intent.getStringExtra("description")
        val imgUrl = intent.getStringExtra("imgUrl")
        val modelo = intent.getStringExtra("modelo")
        val placa = intent.getStringExtra("placa")
        val precoDiaria = intent.getStringExtra("precoDiaria")
        val quilometragem = intent.getStringExtra("quilometragem")
        val statusveiculo = intent.getStringExtra("statusveiculo")



        this.cor.setText(cor)
        this.description.setText(description)
        this.imgUrl.setText(imgUrl)
        this.modelo.setText(modelo)
        this.placa.setText(placa)
        this.precoDiaria.setText(precoDiaria)
        this.quilometragem.setText(quilometragem)
        this.statusveiculo.setText(statusveiculo)


        //carregaFoto(fotoProduct,imgUrl)

    }

    /*fun carregaFoto(fotoProduct: ImageView,imgUrl:String){
        Thread{
            fun run(){
                var url:URL  = URL(imgUrl)
                var conexao: HttpURLConnection = url.openConnection() as HttpURLConnection
                var input: InputStream = conexao.inputStream
                val bitImg: Bitmap = BitmapFactory.decodeStream(input)

                //val imgAux: Bitmap = bitImg
                        handler.post{ Runnable {
                            fun run(){
                                fotoProduct.setImageBitmap(bitImg)
                            }
                        }
                        }
            }
        }.start()
    }*/

    fun cadastrar(v: View) {
        val productDTO: ProductDTO = ProductDTO(id,cor.text.toString(),description.text.toString(),imgUrl.text.toString(),modelo.text.toString(),placa.text.toString(),precoDiaria.text.toString().toInt(),quilometragem.text.toString().toInt(),statusveiculo.text.toString())
        val tokenAutorizacao = getSharedPreferences("dados", 0).getString("token", "")
        RetrofitClient().api.alterarProduct(id, "Bearer $tokenAutorizacao", productDTO).enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.code() == 200) {
                    Toast.makeText(this@AlteracaoDeProdutosActivity, "Alteração realizada", Toast.LENGTH_LONG).show();
                    startActivity(Intent(this@AlteracaoDeProdutosActivity, ListagemDeProdutosActivity::class.java))
                }else Toast.makeText(this@AlteracaoDeProdutosActivity, "Falha", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("erro", t.message)
            }
        })
    }
}