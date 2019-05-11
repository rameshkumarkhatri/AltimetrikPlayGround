package com.app.androidapp.service


import com.google.gson.JsonArray
import com.mobifyall.altimetricplayground.model.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Url

interface WebServiceApi {

    @GET("vehicles/{vehicalId}")
    fun getVehicalDetail(@Path("vehicalId") vehicalId: String, @Header("authorization") authorization: String): Observable<Model.Vehical>;

    @GET
    fun getVehicalImage(@Url imageURL: String): Observable<Model.VehicalImage>;

    @GET
    fun getVehicleStaus(@Url url: String, @Header("authorization") authorization: String): Observable<JsonArray>;

    @GET("vehicles/{vehicalId}/{postParameter}")
    fun getFuelStatus(@Path("vehicalId") vehicalId: String, @Path("postParameter") postParameter: String, @Header("authorization") authorization: String): Observable<Model.FuelCharge>;
}

