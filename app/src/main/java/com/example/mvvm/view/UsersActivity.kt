package com.example.mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.adapters.UsersAdapter
import com.example.mvvm.databinding.ActivityUsersBinding
import com.example.mvvm.extensions.showToast
import com.example.mvvm.factories.MyViewModelFactory
import com.example.mvvm.retrofitResponses.ResponseHandler
import com.example.mvvm.retrofitResponses.User
import com.example.mvvm.utils.MyApplication
import com.example.mvvm.viewmodel.UsersViewModel
import com.google.gson.Gson
import javax.inject.Inject

class UsersActivity : AppCompatActivity(){

    @Inject
    lateinit var mViewModelFactory: MyViewModelFactory

     lateinit var binding:ActivityUsersBinding
     var usersList:ArrayList<User> = ArrayList()
     lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_users)
        binding.toolbar.setTitle(getString(R.string.users));

        var component= MyApplication.apiComponent
        component.inject(this)
        var viewModel= ViewModelProvider(this,mViewModelFactory).get(UsersViewModel::class.java)

        initializeAdpter()
        observeChanges(viewModel)
        viewModel.getUsers()
    }

    private fun observeChanges(usersViewModel : UsersViewModel) {
        usersViewModel.users.observe(this, Observer {
            when (it) {
                is ResponseHandler.Success -> {
                    Log.e("responseisss", "Success" )
                    binding.shimmerFrameLayout.visibility = View.GONE
                    updateAdapter(it.value.data)
                }
                is ResponseHandler.Failure -> {
                    binding.shimmerFrameLayout.visibility = View.GONE
                    Log.e("responseisss", " Failured" )
                    showToast(getString(R.string.something_went_wrong))
                }
                is ResponseHandler.IsLoading -> {
                    Log.e("responseisss", " Loading" )
                    binding.shimmerFrameLayout.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initializeAdpter() {
        binding.shimmerFrameLayout.startShimmerAnimation()
        binding.recyclerview.apply {
            layoutManager=LinearLayoutManager(this@UsersActivity)
            usersAdapter=UsersAdapter(usersList,this@UsersActivity)
            adapter=usersAdapter
        }
    }

    private fun updateAdapter(it: List<User>) {
        binding.recyclerview.visibility= View.VISIBLE
        usersList.clear();
        usersList.addAll(it!!)
        usersAdapter.notifyDataSetChanged()
        if (usersList.size==0) showToast("No Users Found..")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.shimmerFrameLayout.stopShimmerAnimation()
    }

}