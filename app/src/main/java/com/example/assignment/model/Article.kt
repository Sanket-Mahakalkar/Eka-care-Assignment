package com.example.assignment.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val publishedAt: String? = "",
    val author: String? = "",
    val urlToImage: String? = "",
    val description: String? = "",
    val title: String? = "",
    val url: String? = "",
    val content: String? = "",
    var isFav: Boolean = false
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(id)
        parcel.writeString(publishedAt)
        parcel.writeString(author)
        parcel.writeString(urlToImage)
        parcel.writeString(description)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(content)
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}


