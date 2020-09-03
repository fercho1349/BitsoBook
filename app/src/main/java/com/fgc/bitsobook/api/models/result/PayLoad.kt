package com.fgc.bitsobook.api.models.result

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PayLoad() : Serializable{
    @SerializedName("book")
    var book: String? = null
    @SerializedName("volume")
    var volume: String? = null
    @SerializedName("high")
    var high: String? = null
    @SerializedName("last")
    var last: String? = null
    @SerializedName("low")
    var low: String? = null
    @SerializedName("vwap")
    var vwap: String? = null
    @SerializedName("ask")
    var ask: String? = null
    @SerializedName("bid")
    var bid: String? = null
    @SerializedName("created_at")
    var created_at: String? = null
}