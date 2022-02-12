package com.brainx.koindemoproject.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.brainx.koindemoproject.adapter.UserAdapter
import com.brainx.koindemoproject.databinding.ActivityMainBinding
import com.brainx.koindemoproject.utils.SharedPreference
import com.brainx.koindemoproject.utils.beGoneIf
import com.brainx.koindemoproject.utils.beVisibleIf
import com.brainx.koindemoproject.utils.showToast
import com.brainx.koindemoproject.viewIntent.MainActivityIntent
import com.brainx.koindemoproject.viewState.MainActivityState
import com.brainx.koindemoproject.viewmodels.UserViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        dataBinding()
        setRecyclerView()
        userListObservable()
    }

    private fun setBinding(){
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
    }

    private fun dataBinding(){
        mViewBinding.listener = this
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
        lifecycleScope.launch {
            mViewModel.state.collect {
                when(it){
                    is MainActivityState.Idle->{

                    }
                    is MainActivityState.Loading->{
//                        hideShowProgressBar(show = true)
                        hideShowViews(false)
                    }
                    is MainActivityState.Users->{
//                        hideShowProgressBar(show = false)
                        hideShowViews(true)
                        it.user?.let { it1 -> userAdapter.setData(it1) }
                    }
                    is MainActivityState.Error->{
//                        hideShowProgressBar(show = false)
                        hideShowViews(true)
                        showToast(it.error)
                    }
                }
            }
        }
    }

    private fun hideShowViews(show:Boolean){
        mViewBinding.apply {
            btnFetch.beGoneIf(show)
            userRecyclerview.beVisibleIf(show)
        }
    }

    fun fetchUser(view:View){
        lifecycleScope.launch{
            mViewModel.userIntent.send(MainActivityIntent.FetchUsers)
        }
    }
}