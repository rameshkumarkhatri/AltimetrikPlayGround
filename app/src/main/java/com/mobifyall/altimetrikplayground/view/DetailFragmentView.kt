package com.mobifyall.altimetrikplayground.view

import com.google.gson.JsonArray
import com.mobifyall.altimetricplayground.model.Model

interface DetailFragmentView {
    fun showImages(image: Model.VehicalImage)
    fun saveImages(image: Model.VehicalImage)
    fun showErrorImage(error: Throwable)
    fun showFuel(model: Model.FuelCharge?)
    fun showFuel(model: Model.Charge?)
    fun saveFuel(result: Model.FuelCharge?)
    fun saveStatus(result: JsonArray?)
    fun showStatusData(status: String)
}