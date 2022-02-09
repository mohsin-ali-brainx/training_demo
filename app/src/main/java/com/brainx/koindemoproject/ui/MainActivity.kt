package com.brainx.koindemoproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brainx.koindemoproject.adapter.UserAdapter
import com.brainx.koindemoproject.databinding.ActivityMainBinding
import com.brainx.koindemoproject.utils.SharedPreference
import com.brainx.koindemoproject.viewmodels.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var mViewBinding: ActivityMainBinding
    private val sharedPreference : SharedPreference by inject()
    private val mViewModel : UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setRecyclerView()
        userListObservable()
    }

    private fun setBinding(){
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
    }

    private fun setRecyclerView(){
        mViewBinding.userRecyclerview.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            userAdapter = UserAdapter()
            adapter = userAdapter
            setHasFixedSize(true)
        }
    }

    private fun userListObservable(){
        mViewModel.users.observe(this,{
            userAdapter.setData(it)
        })
    }
}