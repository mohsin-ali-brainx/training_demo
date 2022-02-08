package com.brainx.koindemoproject.di

import android.content.Context
import com.brainx.koindemoproject.network.`interface`.ApiService
import com.brainx.koindemoproject.repository.UserRepository
import com.brainx.koindemoproject.utils.SharedPreference
import com.brainx.koindemoproject.viewmodels.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.brainx.koindemoproject.BuildConfig

private fun providesOkHttpClient(mUserDataStore: SharedPreference): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder().addInterceptor(logging)
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
//                        .header("Content-Type", "application/json")
//                        .header("access-token",mUserDataStore.token?:"")
//                        .header("client", mUserDataStore.clientid?:"")
//                        .header("uid",mUserDataStore.userData?.uid?:"")
                    .build()
            )
        }.build()
}


private fun provideRetrofitService(mUserDataStore: SharedPreference): ApiService =
    Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(providesOkHttpClient(mUserDataStore))
        .build()
        .create(ApiService::class.java)


private fun provideSharedPreferenceManager(context: Context) =
    SharedPreference(context)

val appModule = module {
    single { provideSharedPreferenceManager(get()) }
    single { providesOkHttpClient(get()) }
    single { provideRetrofitService(get()) }
}

val repoModule = module {
    single {
        UserRepository(get())
    }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }
}