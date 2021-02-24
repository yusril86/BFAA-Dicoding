package com.yusdroid.consumerapp

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.appcompat.app.AppCompatActivity
import com.yusdroid.consumerapp.adapter.FavoriteAdapter
import com.yusdroid.consumerapp.utils.DatabaseContract.CONTENT_URI
import com.yusdroid.consumerapp.utils.HelperProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var adapterFavorite: FavoriteAdapter = FavoriteAdapter()
    private var helperProvider:HelperProvider = HelperProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler){
            override fun onChange(selfChange: Boolean) {
                showFavorite()
            }
        }
        contentResolver.registerContentObserver(CONTENT_URI,true,myObserver)
        showFavorite()
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            setHasFixedSize(true)
            adapter = adapterFavorite
        }
    }

    private fun showFavorite(){
        GlobalScope.launch(Dispatchers.IO){
            val deferred = async(Dispatchers.IO){
                val sCursor = contentResolver.query(CONTENT_URI,null,null,null,null)
                helperProvider.mapCursorToArrayList(sCursor)
            }
            val mFavorite = deferred.await()
            if (mFavorite.size > 0) {
                adapterFavorite.updateAdapter(mFavorite)
            } else {
                adapterFavorite.updateAdapter(arrayListOf())

            }
        }
    }


}