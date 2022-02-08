package com.brainx.koindemoproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.brainx.koindemoproject.R
import com.brainx.koindemoproject.databinding.ActivityMainBinding
import com.brainx.koindemoproject.utils.SharedPreference
import com.brainx.koindemoproject.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding
    private val sharedPreference : SharedPreference by inject()
    private val viewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        sharedPreference.isLogin = true
        mViewBinding.tvTitle.text = sharedPreference.isLogin.toString()
    }
}