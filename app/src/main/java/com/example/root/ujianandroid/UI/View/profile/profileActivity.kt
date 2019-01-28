package com.example.root.ujianandroid.UI.View.profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.root.ujianandroid.R
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class profileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        backprofile.onClick {
            finish()
        }
    }
}
