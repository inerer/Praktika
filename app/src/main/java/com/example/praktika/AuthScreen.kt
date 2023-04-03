package com.example.praktika

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

class AuthScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_screen)
        val authButton = findViewById<Button>(R.id.enterButton)
        val loginBox = findViewById<EditText>(R.id.loginEditText)
        val passwordBox = findViewById<EditText>(R.id.passwordEditText)

        authButton.setOnClickListener{
            Thread(Runnable {
                var token = "";
                val connectionString = resources.getString(R.string.connectionString)
                var requestParameters =
                    URLEncoder.encode("Login", "UTF-8") +
                        "="+ URLEncoder.encode(loginBox.text.toString(), "UTF-8") +"&"+
                        URLEncoder.encode("Password", "UTF-8") +
                        "="+ URLEncoder.encode(passwordBox.text.toString(), "UTF-8")

                val connection  = URL(connectionString+"/api/User/login?$requestParameters")
                with(connection.openConnection() as HttpURLConnection){
                    requestMethod = "POST"
                    if(responseCode!=200){
                        runOnUiThread{
                            Toast.makeText(
                                this@AuthScreen,
                                "$responseMessage",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    if(responseCode==200){
                        BufferedReader(InputStreamReader(inputStream)).use {
                            val responce = StringBuffer()
                            var inputLine = it.readLine()
                            while (inputLine!=null){
                                responce.append(inputLine)
                                inputLine = it.readLine()
                            }
                            token = responce.toString()

                            runOnUiThread{
                                val nextActivity = Intent(this@AuthScreen, MainActivity::class.java)
                                nextActivity.putExtra("token", token)
                                startActivity(nextActivity)
                            }
                        }
                    }
                }
            }).start()
        }

    }

}