package kotlincodes.com.retrofitwithkotlin.model

import com.google.gson.annotations.SerializedName

data class DataModel(
        @SerializedName("id")
        var id: Int,
        @SerializedName("login")
        val title: String,
        @SerializedName("avatar_url")
        val avatar_url: String,
        @SerializedName("type")
        val type: String
)