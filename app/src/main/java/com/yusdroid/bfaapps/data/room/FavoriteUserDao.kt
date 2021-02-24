package com.yusdroid.bfaapps.data.room

import android.database.Cursor
import androidx.room.*

@Dao
interface FavoriteUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteUser(favorite : Favorite):Long

    @Query("SELECT * FROM favorite_table")
    fun getUserFavorite() : List<Favorite>

    @Delete
    fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_table WHERE username = :name")
    fun getStatus(name : String) : Favorite

    @Query("SELECT * FROM favorite_table")
    fun getCursorUser() : Cursor

    @Query("DELETE FROM favorite_table where username = :name")
    fun deleteByUserLogin(name: String):Int

}