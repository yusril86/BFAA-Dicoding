package com.yusdroid.bfaapps.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite_table")
@Parcelize
data class Favorite(
        @PrimaryKey val username: String,
        @ColumnInfo(name = "id") val id: Int?,
        @ColumnInfo(name = "avatar_url") val avatarUrl: String?
):Parcelable
