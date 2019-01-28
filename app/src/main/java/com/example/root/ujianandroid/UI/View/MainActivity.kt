package com.example.root.ujianandroid.UI.View

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.View.favorite.FavoriteFragment
import com.example.root.ujianandroid.UI.View.history.HistoryFragment
import com.example.root.ujianandroid.UI.View.home.ItemFragment
import com.example.root.ujianandroid.UI.View.profile.profileActivity
import com.example.root.ujianandroid.UI.View.search.searchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custombar.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setFragment(ItemFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                setFragment(HistoryFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                setFragment(FavoriteFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(ItemFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        btnsearch.onClick {
            startActivity<searchActivity>()
        }
        account.onClick {
                startActivity<profileActivity>()
        }
    }
    fun setFragment(fragment : Fragment){

        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }
}
