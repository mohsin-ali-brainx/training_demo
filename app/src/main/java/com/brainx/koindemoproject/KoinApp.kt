package com.brainx.koindemoproject

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.brainx.koindemoproject.di.appModule
import com.brainx.koindemoproject.di.repoModule
import com.brainx.koindemoproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinApp : Application() , LifecycleObserver {

    companion object{
        var isAppInForground : Boolean = true
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        startKoin{
            androidLogger()
            androidContext(this@KoinApp)
            modules(appModule,repoModule,viewModelModule)
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        //App in background
        isAppInForground = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        //App in foreground
        isAppInForground = true
    }

}