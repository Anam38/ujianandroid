package com.example.root.ujianandroid.UI.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.RoomDatabase.setup.DataBaseSetup
import com.example.root.ujianandroid.UI.View.Setup.SetupActivity
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({

            async(kotlinx.coroutines.experimental.android.UI){
                var check = bg {
                    DataBaseSetup.getDatabaseSetup(this@SplashActivity).setup().getAll()
                }
                if (check.await().toString() == "[]"){
                    startActivity<SetupActivity>()
                }else{
                    startActivity<MainActivity>()
                }
            }
        },3000)
    }
}
