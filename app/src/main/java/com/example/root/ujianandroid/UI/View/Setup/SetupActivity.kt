package com.example.root.ujianandroid.UI.View.Setup

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.root.ujianandroid.R
import kotlinx.android.synthetic.main.activity_setup.*


class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        val adapter = TabAdapter(getSupportFragmentManager())
        pagersetup.setAdapter(adapter)

    }
    internal inner class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(i: Int): Fragment? {

            var fragment: Fragment? = null

            when (i) {
                0 -> fragment = setupFragment1()
                1 -> fragment = setupFragment2()
                2 -> fragment = setupFragment3()
            }

            return fragment
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "satu"
                1 -> return "dua"
                2 -> return "tiga"
                3 -> return "empat"
            }

            return super.getPageTitle(position)
        }
    }
}
