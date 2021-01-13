package com.angoti.projetointegrador4_2020.activities.activitiesAuth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.angoti.projetointegrador4_2020.R
import com.angoti.projetointegrador4_2020.activities.MainActivity
import com.angoti.projetointegrador4_2020.dto.LoginDTO
import com.angoti.projetointegrador4_2020.httpclient.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View) {
        val email = findViewById<EditText>(R.id.et_login_email).text.toString()
        val senha = findViewById<EditText>(R.id.et_login_senha).text.toString()
        val loginDTO = LoginDTO(email, senha, "")
        RetrofitClient().api.login(loginDTO).enqueue(object : Callback<LoginDTO?> {
            override fun onResponse(call: Call<LoginDTO?>, response: Response<LoginDTO?>) {
                if (response.code() == 200) {
                    Toast.makeText(this@LoginActivity, "Usuário autenticado", Toast.LENGTH_SHORT)
                        .show()
                    val sp = getSharedPreferences("dados", 0)
                    val editor = sp.edit()
                    editor.putString("token", response.body()?.token)
                    editor.apply()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "Erro: verifique email e senha", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<LoginDTO?>, t: Throwable) {
                Log.e("erro", "Falha: ${t.message}")
            }
        })
    }
}