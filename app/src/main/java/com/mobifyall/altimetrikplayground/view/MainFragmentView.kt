package com.mobifyall.altimetrikplayground.view

import androidx.fragment.app.Fragment
import com.mobifyall.altimetricplayground.model.Model

interface MainFragmentView {

    fun addClickListeners()
    fun showEmptyError()
    fun showResult(result: Model.Vehical?)
    fun showApiError(error: Throwable?)
}
