package com.yusdroid.bfaapps.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yusdroid.bfaapps.R
import com.yusdroid.bfaapps.adapter.UserAdapter
import com.yusdroid.bfaapps.ui.activity.DetailUserViewModel
import kotlinx.android.synthetic.main.fragment_detail_follow.*

class DetailFollowFragment(
    private val position: Int,
    private val userName: String
) : Fragment() {
    private lateinit var viewModel: DetailUserViewModel
    private var adapterUser: UserAdapter = UserAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_follow, container, false)

    }

   /* companion object {
        private const val  ARG_FOLLOW = "arg_follow"
        private const val ARG_USERNAME = "arg_username"
         fun newInstance(userName: String, follow: String) =
                 DetailFollowFragment().apply {
                     arguments = Bundle().apply {
                         putString(ARG_FOLLOW, follow)
                         putString(ARG_USERNAME, userName)
                     }
                 }
    }*/

    /*companion object {
        private const val  ARG_POSITION = "arg_position"
        private const val ARG_USERNAME = "arg_username"
        fun newInstance(userName: String, follow: Int) =
                DetailFollowFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_POSITION, follow)
                        putString(ARG_USERNAME, userName)
                    }
                }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
      /*  val userName = arguments?.getString(ARG_USERNAME)
        val position = arguments?.getInt(ARG_POSITION)*/
        if(position == 0 ){
            getFollowersUser(userName)
        }else{
            getFollowingUser(userName)
        }
        initRecyclerView() //
    }

    private fun getFollowersUser(userName: String){
        viewModel.getFollowers(userName)
        viewModel.getDataUsers().observe(viewLifecycleOwner,{
            it.let {
                adapterUser.updateAdapter(ArrayList(it))
                progressBar_follow.visibility = View.GONE
            }
        })
    }

    private fun getFollowingUser(userName: String){
        viewModel.getFollowing(userName)
        viewModel.getDataUsers().observe(viewLifecycleOwner,{
            it.let {
                adapterUser.updateAdapter(ArrayList(it))
                progressBar_follow.visibility = View.GONE
            }
        })
    }

    private fun initRecyclerView(){
        rv_fragment.apply {
            adapter = adapterUser
            setHasFixedSize(true)
        }

    }

}