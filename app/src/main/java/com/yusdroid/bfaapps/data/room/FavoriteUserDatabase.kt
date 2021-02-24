package com.yusdroid.bfaapps.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteUserDatabase :RoomDatabase() {
    abstract fun favoriteUserDao() :FavoriteUserDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteUserDatabase? = null
        fun getDatabase(context: Context) :FavoriteUserDatabase?{
            if (INSTANCE == null ){
                synchronized(this){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,FavoriteUserDatabase::class.java,"favorite_table"
                        )
                                .allowMainThreadQueries()
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}