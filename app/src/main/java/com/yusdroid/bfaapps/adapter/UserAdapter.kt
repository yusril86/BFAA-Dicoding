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
import com.yusdroid.bfaapps.ui.activity.DetailUserActivity
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.itemViewHolder>(){
    private val mListUser : MutableList<UserResponse> = ArrayList()

    fun updateAdapter(list: ArrayList<UserResponse>){
        mListUser.addAll(list)
        notifyDataSetChanged()
    }

    fun clearAll(){
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
                    .load(avatar)
                    .apply(RequestOptions().override(50,50))
                    .into(ci_photo)

                setOnClickListener {
                    val intentPassing = Intent(context,DetailUserActivity::class.java)
                    intentPassing.putExtra(DetailUserActivity.EXTRA_LOGIN, mListUser[position].username)
                    intentPassing.putExtra(DetailUserActivity.EXTRA_AVATAR, mListUser[position].avatar)
                    intentPassing.putExtra(DetailUserActivity.EXTRA_ID, mListUser[position].id)
                    context.startActivity(intentPassing)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return mListUser.size
    }

    inner class itemViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }

}