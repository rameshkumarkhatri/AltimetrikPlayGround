package com.mobifyall.altimetrikplayground.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mobifyall.altimetricplayground.model.Model
import com.mobifyall.altimetricplayground.view.MainActivityVIew

class MainActivityViewModel : ViewModel(){
    fun setFragment(id: String, desc: String) {
        if(id.isEmpty() || desc.isEmpty()){
            view.gotoMainFragment()
        }else {
            val gson = Gson()
            val model = gson.fromJson(desc, Model.Vehical::class.java)
            view.gotoDescFragment(model)
        }
    }

    lateinit var view: MainActivityVIew
}