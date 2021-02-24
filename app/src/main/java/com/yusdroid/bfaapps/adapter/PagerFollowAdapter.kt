package com.yusdroid.bfaapps.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yusdroid.bfaapps.ui.fragment.DetailFollowFragment

class PagerFollowAdapter(fm : FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {

     var userName =""


    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
       /* return if (position == 0) {
            DetailFollowFragment.newInstance(userName ,followers) //endPoint untuk followers
        }else{
            DetailFollowFragment.newInstance(userName ,following) //endPoint following
        }*/
      /*   return if (position == 0) {
            DetailFollowFragment.newInstance(userName ,position)
        }else{
            DetailFollowFragment.newInstance(userName ,position)
        }*/

        return DetailFollowFragment(position,userName)

    }

}