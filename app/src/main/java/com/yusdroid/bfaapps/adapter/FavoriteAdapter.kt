package com.yusdroid.bfaapps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusdroid.bfaapps.R
import com.yusdroid.bfaapps.data.model.UserResponse
import com.yusdroid.bfaapps.data.room.Favorite
import com.yusdroid.bfaapps.ui.activity.DetailUserActivity
import kotlinx.android.synthetic.main.item_user.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.itemViewHolder>() {
    private val mListUserFavorite : MutableList<Favorite> = ArrayList()

    fun updateAdapter(userList : List<Favorite>){
        mListUserFavorite.addAll(userList)
        notifyDataSetChanged()
    }
    fun clear(){
        mListUserFavorite.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
       return itemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }
    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.itemView.apply {
            mListUserFavorite[position].apply {
                tv_name.text = username
                Glide.with(context)
                        .load(avatarUrl)
                        .apply(RequestOptions().override(60,60))
                        .into(ci_photo)
                setOnClickListener {
                    val intent = Intent(context,DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_LOGIN,mListUserFavorite[position].username)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mListUserFavorite.size
    }

    class itemViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }
}
