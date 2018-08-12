package io.pavelshackih.imageviewer.model

import com.google.gson.annotations.SerializedName

data class Photos(
        @SerializedName("photos") val page: Page,
        @SerializedName("stat") val status: String
)
