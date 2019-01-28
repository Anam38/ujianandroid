package com.example.root.ujianandroid.UI.View.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.View.MainActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.searchbar.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class searchActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnbacksearch.onClick {
            finish()
        }
        btnsearchvideo.onClick {
            startActivity<MainActivity>("param" to inputsearch.text.toString())
        }

        btnsearchmusic.onClick {
            startActivity<MainActivity>("param" to "music")
        }
        btnsearchgame.onClick {
            startActivity<MainActivity>("param" to "game")
        }
        btnsearchberita.onClick {
            startActivity<MainActivity>("param" to "berita")
        }
        btnsearcmovie.onClick {
            startActivity<MainActivity>("param" to "film")
        }
        btnsearcsport.onClick {
            startActivity<MainActivity>("param" to "olahraga")
        }
    }
}
