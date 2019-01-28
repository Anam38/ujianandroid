package com.example.root.ujianandroid.UI.View.Setup


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.setup.SetupModel
import com.example.root.ujianandroid.UI.RoomDatabase.setup.DataBaseSetup
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class setupFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup_fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = SetupModel()
        data.status = "ada"
        async(UI){
            bg {
                activity?.let { DataBaseSetup.getDatabaseSetup(it).setup().insertAll(data) }
            }
        }
    }

}
