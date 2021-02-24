package com.yusdroid.consumerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusdroid.consumerapp.R
import com.yusdroid.consumerapp.data.model.Favorite
import kotlinx.android.synthetic.main.item_user.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.itemViewHolder>() {
    private val mListUser : MutableList<Favorite> = ArrayList()

    fun updateAdapter(userList : List<Favorite>){
        mListUser.addAll(userList)
        notifyDataSetChanged()
    }
    fun clear(){
        mListUser.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
       return itemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }
    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.itemView.apply {
            mListUser[position].apply {
                tv_name.text = username
                Glide.with(context)
                        .load(avatarUrl)
                        .apply(RequestOptions().override(60,60))
                        .into(ci_photo)
            }
        }
    }

    override fun getItemCount(): Int {
        return mListUser.size
    }

    class itemViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }
}
