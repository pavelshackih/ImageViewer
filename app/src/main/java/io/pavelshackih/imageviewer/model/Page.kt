package io.pavelshackih.imageviewer.model

import com.google.gson.annotations.SerializedName

data class Page(
        val page: Int,
        val pages: Int,
        @SerializedName("perpage") val perPage: Int,
        val total: Int,
        @SerializedName("photo") val photos: List<Photo>
)