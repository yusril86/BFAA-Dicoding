package com.yusdroid.bfaapps.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yusdroid.bfaapps.R
import com.yusdroid.bfaapps.adapter.FavoriteAdapter
import com.yusdroid.bfaapps.data.room.FavoriteUserDatabase
import kotlinx.android.synthetic.main.activity_favorite_user.*

private var adapterFavorite : FavoriteAdapter = FavoriteAdapter()

 class FavoriteUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user)
        getDataFavorite()

        rv_favorite.apply {
            setHasFixedSize(true)
            adapter = adapterFavorite
        }


    }
     private fun getDataFavorite() {
         val database = FavoriteUserDatabase.getDatabase(applicationContext)
         val dao = database?.favoriteUserDao()
         val listFavorite = dao?.getUserFavorite()
         adapterFavorite.clear()
         rv_favorite.apply {
             setHasFixedSize(true)
             adapter = adapterFavorite
             adapterFavorite.updateAdapter(listFavorite!!)
         }
     }



 }
