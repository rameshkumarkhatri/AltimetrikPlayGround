package com.mobifyall.altimetricplayground.view

import androidx.fragment.app.Fragment
import com.mobifyall.altimetricplayground.model.Model

interface MainActivityVIew {
    public fun changeFragment(fragment: Fragment)
    public fun changeFragmentWithBackStack(fragment: Fragment)
    fun gotoMainFragment()
    fun gotoDescFragment(model : Model.Vehical)
}