package com.yusdroid.bfaapps.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.yusdroid.bfaapps.R
import com.yusdroid.bfaapps.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : AppCompatActivity() {
   private lateinit var viewModel:UserActivityViewModel
    private var adapterHome: UserAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(UserActivityViewModel::class.java)

        showUsers()
      /*  recycler_view.apply {
            adapter = adapterHome
            setHasFixedSize(true)
        }*/initRecyclerView()

        searchView_user.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    recycler_view.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    adapterHome.clearAll()
                    searchUser(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun showUsers(){
        viewModel.fetchDataUsers()
        viewModel.getDataUsers().observe(this,{
            it.apply {
                adapterHome.updateAdapter(ArrayList(it))
                progressBar.visibility= View.GONE
            }
        })
    }

    private fun searchUser(user:String){
        viewModel.searchUser(user)
        recycler_view.visibility = View.VISIBLE
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            adapter = adapterHome
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
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
        if (item.itemId == R.id.action_alarm){
            val intentAlarm = Intent(this,SettingAlarmActivity::class.java)
            startActivity(intentAlarm)
        }
        return super.onOptionsItemSelected(item)
    }
}
