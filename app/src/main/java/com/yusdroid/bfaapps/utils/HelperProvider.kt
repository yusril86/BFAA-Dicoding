package com.yusdroid.bfaapps.utils

import android.net.Uri

object HelperProvider {


    const val AUTHORITY = "com.yusdroid.bfaapps"
    const val TABLE_NAME = "favorite_table"

    const val uriString = "content://$AUTHORITY/$TABLE_NAME"
     val CONTENT_URI: Uri = Uri.parse(uriString)
}