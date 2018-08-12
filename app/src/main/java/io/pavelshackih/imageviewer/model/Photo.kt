package io.pavelshackih.imageviewer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: Int,
        val title: String,
        @SerializedName("ispublic") val isPublic: Int,
        @SerializedName("isfriend") val isFriend: Int,
        @SerializedName("isfamily") val isFamily: Int
) : Parcelable {
    fun getPhotoSource(): String = "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"
}
