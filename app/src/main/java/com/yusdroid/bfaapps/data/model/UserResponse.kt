package com.yusdroid.bfaapps.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @field:SerializedName("login")
    var username: String? = "",

    @field:SerializedName("name")
    var name: String? = "",

    @field:SerializedName("avatar_url")
    var avatar: String? = "",

    @field:SerializedName("location")
    var location: String? = "",

    @field:SerializedName("company")
    var company: String? = "",

    @field:SerializedName("public_repos")
    var repository: String? = "",

    @field:SerializedName("followers")
    var follower: String? = "",

    @field:SerializedName("following")
    var following: String? = "",

    @field:SerializedName("html_url")
    var link: String? = "",

    @field:SerializedName("id")
    var id: Int?
): Parcelable

data class SearchResponse(
    @field:SerializedName("items")
    val items: ArrayList<UserResponse>
)
