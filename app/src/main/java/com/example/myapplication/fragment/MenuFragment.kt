package com.example.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myapplication.DTO.AuthRequestDTO
import com.example.myapplication.DTO.OkResponseDTO
import com.example.myapplication.DTO.RegisterRequestDTO
import com.example.myapplication.Entity.User
import com.example.myapplication.R
import com.example.myapplication.RetrofitServices
import com.example.myapplication.retrofit.RetrofitClient
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MenuFragment(
    val user: User
) : Fragment() {

    var myExecutor: ExecutorService = Executors.newFixedThreadPool(2)

    private fun sendRegisterRequest(mail: String, pwd: String) {
        val apiService: RetrofitServices =
            RetrofitClient.getClient().create(RetrofitServices::class.java)
        val call = apiService.register(RegisterRequestDTO(mail, pwd))
        myExecutor.execute {
            val responseDTO: OkResponseDTO = call.execute().body()!!
            if (responseDTO.success) {
                user.mail = mail
                user.pwd = pwd
                user.isAuth = true
                activity?.runOnUiThread {
                    authSuccess(user.mail, user.pwd)
                }
            } else {
                activity?.runOnUiThread {
                    val text = "Ошибка сервера, попробуйте ещё раз"
                    Toast.makeText(this.context, text, text.length).show()
                }
            }
        }
    }

    private fun sendAuthRequest(mail: String, pwd: String) {
        val apiService: RetrofitServices =
            RetrofitClient.getClient().create(RetrofitServices::class.java)
        val call = apiService.auth(AuthRequestDTO(mail, pwd))
        myExecutor.execute {
            val responseDTO: OkResponseDTO = call.execute().body()!!
            if (responseDTO.success) {
                user.mail = mail
                user.pwd = pwd
                user.isAuth = true
                activity?.runOnUiThread {
                    authSuccess(user.mail, user.pwd)
                }
            } else {
                activity?.runOnUiThread {
                    val text = "Неверный логин и/или пароль"
                    Toast.makeText(this.context, text, text.length).show()
                }
            }
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!user.isAuth) {
            val inButton: Button = view.findViewById(R.id.sign_in)
            val upButton: Button = view.findViewById(R.id.sign_up)
            val email: EditText = view.findViewById(R.id.email_edit_text)
            val pwd: EditText = view.findViewById(R.id.password_edit_text)
            inButton.setOnClickListener {
                val emailString = email.text.toString()
                val pwdString = pwd.text.toString()

                if (email.length() == 0 || pwd.length() == 0) {
                    val text = "Введите логин/пароль"
                    Toast.makeText(this.context, text, text.length).show()
                } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                    val text = "Ошибка при вводе почты"
                    Toast.makeText(this.context, text, text.length).show()
                } else if (pwd.length() < 8 || pwd.length() > 64) {
                    val text = "Длина пароля не может быть меньше 8, и более 63 символов"
                    Toast.makeText(this.context, text, text.length).show()
                } else {
                    sendAuthRequest(emailString, pwdString)
                }
            }
            upButton.setOnClickListener {
                val emailString = email.text.toString()
                val pwdString = pwd.text.toString()
                if (email.length() == 0 || pwd.length() == 0) {
                    val text = "Введите логин/пароль"
                    Toast.makeText(this.context, text, text.length).show()
                } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                    val text = "Ошибка при вводе почты"
                    Toast.makeText(this.context, text, text.length).show()
                } else if (pwd.length() < 8 || pwd.length() > 64) {
                    val text = "Длина пароля не может быть меньше 8, и более 63 символов"
                    Toast.makeText(this.context, text, text.length).show()
                } else {
                    sendRegisterRequest(emailString, pwdString)
                }
            }
        } else {
            authSuccess(user.mail, user.pwd)
        }
    }

    private fun authSuccess(mail: String, pwd: String) {
        val inButton: Button = this.requireView().findViewById(R.id.sign_in)
        val upButton: Button = this.requireView().findViewById(R.id.sign_up)
        val emailText: EditText = this.requireView().findViewById(R.id.email_edit_text)
        val pwdText: EditText = this.requireView().findViewById(R.id.password_edit_text)
        inButton.visibility = View.INVISIBLE
        upButton.visibility = View.INVISIBLE
        emailText.visibility = View.INVISIBLE
        pwdText.visibility = View.INVISIBLE

        val gridView: GridView = this.requireView().findViewById(R.id.gridViewUserAnnouncement)
        val up_text: TextView = this.requireView().findViewById(R.id.lk_text)
        gridView.visibility = View.VISIBLE
        up_text.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.menu_fragment,
            container, false
        )
    }

}