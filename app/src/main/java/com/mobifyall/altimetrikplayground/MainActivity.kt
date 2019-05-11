package com.mobifyall.altimetricplayground

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mobifyall.altimetricplayground.model.Model
import com.mobifyall.altimetricplayground.view.MainActivityVIew
import com.mobifyall.altimetrikplayground.R
import com.mobifyall.altimetrikplayground.SharedData
import com.mobifyall.altimetrikplayground.databinding.ActivityMainBinding
import com.mobifyall.altimetrikplayground.fragment.VehicalDetailFragment
import com.mobifyall.altimetrikplayground.viewmodel.MainActivityViewModel
import com.mobifyall.music.MainFragment

class MainActivity : AppCompatActivity(), MainActivityVIew, View.OnClickListener {
    override fun gotoDescFragment(model: Model.Vehical) {
        changeFragment(VehicalDetailFragment.newInstance(model))

    }

    override fun onClick(v: View?) {

    }


    lateinit var binding: ActivityMainBinding;
    lateinit var viewModel: MainActivityViewModel

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, fragment).commit()
    }

    override fun changeFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, fragment).addToBackStack(null).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.view = (this)
        viewModel.setFragment(SharedData.getData(this, SharedData.id), SharedData.getData(this, SharedData.desc))

    }
    override fun gotoMainFragment() {
        changeFragment(MainFragment.newInstance(this))
    }
//
//    val broadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(contxt: Context?, intent: Intent?) {
//
////            if(intent?.getStringExtra("permission"))
//        }
//    };
}