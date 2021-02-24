package com.yusdroid.consumerapp.utils

import android.database.Cursor
import com.yusdroid.consumerapp.data.model.Favorite
import com.yusdroid.consumerapp.utils.DatabaseContract.UserColumns.Companion.COLUMN_NAME_AVATAR_URL
import com.yusdroid.consumerapp.utils.DatabaseContract.UserColumns.Companion.COLUMN_NAME_USERNAME
import com.yusdroid.consumerapp.utils.DatabaseContract.UserColumns.Companion._ID


class HelperProvider {



        fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<Favorite> {
        val favoriteUserList = ArrayList<Favorite>()

        userCursor?.apply {
            while (moveToNext()) {
                val userName =
                    getString(getColumnIndexOrThrow(COLUMN_NAME_USERNAME))
                val id = getInt(getColumnIndexOrThrow(_ID))
                val avatarUrl =
                    getString(getColumnIndexOrThrow(COLUMN_NAME_AVATAR_URL))
                favoriteUserList.add(
                    Favorite(
                        userName,
                        id,
                        avatarUrl
                    )
                )
            }
        }
        return favoriteUserList
    }

    }