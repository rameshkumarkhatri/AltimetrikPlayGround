package com.mobifyall.altimetrikplayground.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.app.androidapp.service.RetrofitFactory
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.mobifyall.altimetricplayground.model.Model
import com.mobifyall.altimetrikplayground.SharedData
import com.mobifyall.altimetrikplayground.view.DetailFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VehicalDetailViewModel : ViewModel() {
    lateinit var view: DetailFragmentView
    fun getImagesFromServer(context: Context, id: String, number: String) {
        val desc = SharedData.getData(context, SharedData.image)
        if (desc.isNotEmpty()) {
            val gson = Gson()
            val model = gson.fromJson(desc, Model.VehicalImage::class.java)
            view.showImages(model)
        } else
            RetrofitFactory.create().getVehicalImage(RetrofitFactory.getImageURLOfVehical("WDD1760421J123456"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    run {
                        view.saveImages(result)
                        view.showImages(result)
                    }
                },
                    { error -> view.showErrorImage(error) })

        val fuel = SharedData.getData(context, SharedData.fuel)

        if (fuel.isNotEmpty()) {
            val gson = Gson()
            val model = gson.fromJson(fuel, Model.FuelCharge::class.java)
            if (model.stateofcharge == null)
                view.showFuel(model)
            else view.showFuel(model.stateofcharge)

        } else
            RetrofitFactory.create().getFuelStatus(id, SharedData.getPostParameterFuel(""), RetrofitFactory.header)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    run {
                        view.saveFuel(result)
                        if (result.stateofcharge == null)
                            view.showFuel(result)
                        else view.showFuel(result.stateofcharge)
                    }
                },
                    { error -> view.showErrorImage(error) })
        val vehicleStatus = SharedData.getData(context, SharedData.status)

        if (vehicleStatus.isNotEmpty()) {
            val gson = Gson()
            val model = gson.fromJson(vehicleStatus, JsonArray::class.java)
            populateStatus(model)

        } else
            RetrofitFactory.create().getVehicleStaus(
                RetrofitFactory.vehicleStatusURL,
                RetrofitFactory.headerForStatusOfVehicle
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    run {
                        view.saveStatus(result)
                        populateStatus(result)
//                        if (result.stateofcharge == null)
//                            view.showFuel(result)
//                        else view.showFuel(result.stateofcharge)
                    }
                },
                    { error -> view.showErrorImage(error) })
    }

    private fun populateStatus(model: JsonArray?) {

        var str = StringBuilder();
        model?.forEach { json ->
            run {
                val v = json.asJsonObject;
                if (v.has("decklidstatus"))
                    str.append(
                        "decklid status ".toUpperCase() + (when (v.get("decklidstatus").asJsonObject.get("value").asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("doorstatusfrontleft"))
                    str.append(
                        "door status front left ".toUpperCase() + (when (v.get("doorstatusfrontleft").asJsonObject.get("value").asString) {
                            "true" -> "<b>Open</b></b><br>"
                            "false" -> "<b>Close</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("doorstatusfrontright"))
                    str.append(
                        "door status fron tright ".toUpperCase() + (when (v.get("doorstatusfrontright").asJsonObject.get(
                            "value"
                        ).asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("doorstatusrearleft"))
                    str.append(
                        "door status rear left ".toUpperCase() + (when (v.get("doorstatusrearleft").asJsonObject.get("value").asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("doorstatusrearright"))
                    str.append(
                        "door status rear right ".toUpperCase() + (when (v.get("doorstatusrearright").asJsonObject.get("value").asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("interiorLightsFront"))
                    str.append(
                        "interior Lights Front ".toUpperCase() + (when (v.get("interiorLightsFront").asJsonObject.get("value").asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("interiorLightsRear"))
                    str.append(
                        "interior Lights Rear ".toUpperCase() + (when (v.get("interiorLightsRear").asJsonObject.get("value").asString) {
                            "true" -> "<b>Open</b><br>"
                            "false" -> "<b>Close</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("lightswitchposition"))
                    str.append(
                        "light switch position ".toUpperCase() + (when (v.get("lightswitchposition").asJsonObject.get("value").asString) {
                            "0" -> "<b>Off</b><br>"
                            else -> "<b>On</b><br>"

                        })
                    )

                if (v.has("readingLampFrontLeft"))
                    str.append(
                        "reading Lamp Front Left ".toUpperCase() + (when (v.get("readingLampFrontLeft").asJsonObject.get(
                            "value"
                        ).asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""

                        })
                    )

                if (v.has("readingLampFrontRight"))
                    str.append(
                        "reading Lamp Front Right ".toUpperCase() + (when (v.get("readingLampFrontRight").asJsonObject.get(
                            "value"
                        ).asString) {
                            "true" -> "<b>On</b><br>"
                            "false" -> "<b>Off</b><br>"
                            else -> ""
                        })
                    )

                if (v.has("rooftopstatus"))
                    str.append(
                        "roof top status ".toUpperCase() + (when (v.get("rooftopstatus").asJsonObject.get("value").asString) {
                            "0" -> "<b>Off</b><br>"
                            else -> "<b>On</b><br>"

                        })
                    )

                if (v.has("sunroofstatus"))
                    str.append(
                        "sun roof status ".toUpperCase() + (when (v.get("sunroofstatus").asJsonObject.get("value").asString) {
                            "0" -> "<b>Off</b><br>"
                            else -> "<b>On</b><br>"

                        })
                    )

                if (v.has("windowstatusfrontleft"))
                    str.append(
                        "window status front left ".toUpperCase() + (when (v.get("windowstatusfrontleft").asJsonObject.get(
                            "value"
                        ).asString) {
                            "0" -> "<b>Off</b><br>"
                            else -> "<b>On</b><br>"
                        })
                    )

                if (v.has("windowstatusfrontright"))
                    str.append(
                        "window status front right ".toUpperCase() + (when (v.get("windowstatusfrontright").asJsonObject.get(
                            "value"
                        ).asString) {
                            "0" -> "<b>Off</b><br>"
                            else -> "<b>On</b><br>"
                        })
                    )

            }

        }
        view.showStatusData(str.toString())
    }
}
