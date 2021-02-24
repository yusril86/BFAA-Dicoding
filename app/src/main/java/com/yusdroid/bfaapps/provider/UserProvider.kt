package com.yusdroid.bfaapps.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.yusdroid.bfaapps.data.room.FavoriteUserDao
import com.yusdroid.bfaapps.data.room.FavoriteUserDatabase
import com.yusdroid.bfaapps.utils.HelperProvider.AUTHORITY
import com.yusdroid.bfaapps.utils.HelperProvider.CONTENT_URI
import com.yusdroid.bfaapps.utils.HelperProvider.TABLE_NAME

class UserProvider : ContentProvider() {
    companion object {
        private const val USER = 1
        private const val USER_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favoriteUserDao: FavoriteUserDao
    }

    init {
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, USER)
        uriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", USER_ID)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val deleted : Int = when(USER_ID){
            uriMatcher.match(uri)-> favoriteUserDao.deleteByUserLogin(uri.lastPathSegment.toString())
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI,null)
        return deleted
    }

    override fun getType(uri: Uri): String? {
       return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
       return null
    }

    override fun onCreate(): Boolean {
        Log.d("testprovider","Create")
        favoriteUserDao = FavoriteUserDatabase.getDatabase(context!!)!!.favoriteUserDao()
        return false
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor {
        val cursor: Cursor
        when (uriMatcher.match(uri)) {
            USER -> {
                cursor = favoriteUserDao.getCursorUser()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                    return cursor
                }
                throw IllegalArgumentException("Unknown URI: $uri")
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
       return 0
    }
}