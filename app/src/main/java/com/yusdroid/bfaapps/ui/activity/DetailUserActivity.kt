package com.yusdroid.bfaapps.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.yusdroid.bfaapps.R
import com.yusdroid.bfaapps.adapter.PagerFollowAdapter
import com.yusdroid.bfaapps.data.model.UserResponse
import com.yusdroid.bfaapps.data.room.Favorite
import com.yusdroid.bfaapps.data.room.FavoriteUserDao
import com.yusdroid.bfaapps.data.room.FavoriteUserDatabase
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.item_user.*

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_LOGIN = "extra_login"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_ID = "extra_id"

    }
    private lateinit var viewModel: DetailUserViewModel
    private var pagerFollowAdapter : PagerFollowAdapter = PagerFollowAdapter(supportFragmentManager,lifecycle)

    private lateinit var database : FavoriteUserDatabase
    private lateinit var dao: FavoriteUserDao

    private var myFavaforite : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        initToolbar()


        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)


        val username: String? = intent.getStringExtra(EXTRA_LOGIN)
        val avatar: String? = intent.getStringExtra(EXTRA_AVATAR)
        val id: Int = intent.getIntExtra(EXTRA_ID,1)
        showUser(username.toString())

        pagerFollowAdapter.userName = username.toString()
        viewPager.adapter =pagerFollowAdapter
        TabLayoutMediator(tab_layout,viewPager){
            tab,position ->
            if (position == 0){
                tab.text = "Followers"
            }else{
                tab.text = "Following"
            }
        }.attach()

        configDatabase()
        statusFavorite()

        btn_favorite.setOnClickListener {
            if (myFavaforite){
                deleteFavorite( Favorite(username.toString(),id,avatar))
                Toast.makeText(this, "Delete Favorite User", Toast.LENGTH_SHORT).show()
            }else{
                insertFavorite(Favorite(username.toString(),id,avatar))
                Toast.makeText(this, "Add Favorite User", Toast.LENGTH_SHORT).show()
            }
        }

        val isFav = dao.getStatus(username.toString())
        if (isFav !== null){
            setFavorite(true)
        }else{
            setFavorite(false)
        }
    }


    private fun showUser(userName:String){
        viewModel.getUser(userName)
        viewModel.dataUser.observe(this,{it.apply {
            Glide.with(this@DetailUserActivity)
                    .load(avatar)
                    .apply(RequestOptions())
                    .into(img_users)
            tv_detail_fullname.text = name
            tv_detail_username.text = username
            tv_detail_location.text = location
            tv_detail_company.text = company
            tv_repoCount.text = repository
            tv_followersCount.text = follower
            tv_followingCount.text = following
        }
        })
    }

    private fun  initToolbar(){
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Detail User"
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    private fun setFavorite(favorite : Boolean){
        if(favorite){
            myFavaforite = true
            btn_favorite.setImageResource(R.drawable.ic_favorite)
        }else{
            myFavaforite = false
            btn_favorite.setImageResource(R.drawable.ic_unfavorite)
        }
    }

    private fun statusFavorite(){
        if (myFavaforite){
            myFavaforite = true
        }else if (!myFavaforite){
            myFavaforite = false
        }
    }

    private fun configDatabase(){
        //Database init
        database = FavoriteUserDatabase.getDatabase(this)!!
        dao = database.favoriteUserDao()
    }

    private fun deleteFavorite(favorite : Favorite){
        dao.deleteFavorite(favorite)
        setFavorite(false)
    }
    private fun insertFavorite(favorite : Favorite){
        setFavorite(true)
        dao.addFavoriteUser(favorite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_language){
            val intentLanguage = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intentLanguage)
        }
        if (item.itemId == R.id.favorite_user) {
            val intentFavorite= Intent(this,FavoriteUserActivity::class.java)
            startActivity(intentFavorite)
        }
        return super.onOptionsItemSelected(item)
    }
}