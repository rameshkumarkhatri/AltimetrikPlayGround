package com.app.androidapp.service

import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {


    companion object {

        val vehicleStatusURL: String = "https://api.mercedes-benz.com/vehicledata_tryout/v1/vehicles/WDB111111ZZZ22222/containers/vehiclestatus"
        val imageURL: String = "https://api.mercedes-benz.com/image_tryout/v1/vehicles/WDD1760421J123456/vehicle?perspectives=EXT020%2CINT1&roofOpen=false&night=false&apikey=Tyt82ndiKG0AdH8TCqe001ROh7RsGOKB"
        //WDD1760421J123456
         fun getImageURLOfVehical( id : String ): String =
            "https://api.mercedes-benz.com/image_tryout/v1/vehicles/$id/vehicle?perspectives=EXT020%2CINT1&roofOpen=false&night=false&apikey=Tyt82ndiKG0AdH8TCqe001ROh7RsGOKB"
        val header = "Bearer a1b2c3d4-a1b2-a1b2-a1b2-a1b2c3d4e5f6";
        val headerForStatusOfVehicle = "Bearer 4c4c444c-v123-4123-s123-4c4c444c4c44";

        fun create(): WebServiceApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.mercedes-benz.com/experimental/connectedvehicle_tryout/v1/")
                .build()

            return retrofit.create(WebServiceApi::class.java)
        }



    }

    val WebServiceApi by lazy {
        create()
    }
    var disposable: Disposable? = null


}