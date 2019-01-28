package com.example.root.ujianandroid.UI.View.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.View.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnregister.onClick {
            startActivity<RegisterActivity>()
        }
    }
}
