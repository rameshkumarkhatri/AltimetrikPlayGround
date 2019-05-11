package com.mobifyall.altimetricplayground.model

import com.google.gson.JsonObject
import java.io.Serializable

object Model {
    data class Vehical(val id : String, val licenseplate : String, val salesdesignation : String, val finorvin : String,
                       val modelyear : String, val colorname : String, val fueltype : String,
                       val powerhp : String, val powerkw : String,
                       val numberofdoors : String, val numberofseats : String) : Serializable

    data class VehicalImage (val vehicle : VehicalI)
    data class VehicalI(val INT1: INT1, val EXT020: EXT020)
    data class INT1 (val url: String)
    data class EXT020 (val url: String)

    data class FuelCharge(val unit : String, val value : String, val retrievalstatus : String, val timestamp : String, val stateofcharge : Charge)
    data class Charge(val unit : String, val value : String, val retrievalstatus : String, val timestamp : String)



}

