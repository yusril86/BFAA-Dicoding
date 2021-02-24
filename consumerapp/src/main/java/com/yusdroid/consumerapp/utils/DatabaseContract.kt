package com.yusdroid.consumerapp.utils

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.yusdroid.bfaapps"
    const val SCHEME = "content"
    const val TABLE_NAME = "favorite_table"

    val CONTENT_URI : Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()

    class UserColumns : BaseColumns {
        companion object {
            const val COLUMN_NAME_USERNAME = "username"
            const val _ID = "id"
            const val COLUMN_NAME_AVATAR_URL = "avatar_url"
        }
    }
}