package com.ranjithnaidu.android.playbook.di

import com.ranjithnaidu.android.playbook.BuildConfig
import com.ranjithnaidu.android.playbook.post.PostsRepository
import com.ranjithnaidu.android.playbook.post.viewmodel.PostDetailViewModel
import com.ranjithnaidu.android.playbook.post.viewmodel.PostHeaderViewModel
import com.ranjithnaidu.android.playbook.post.viewmodel.PostListItemViewModel
import com.ranjithnaidu.android.playbook.post.viewmodel.PostsViewModel
import com.ranjithnaidu.android.playbook.services.PlaybookService
import com.ranjithnaidu.android.playbook.utils.*
import com.squareup.moshi.Moshi
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

/**
 * Core Koin dependency injection module
 */
val container: Module = module {

    viewModel { PostsViewModel() }
    viewModel { PostDetailViewModel() }
    viewModel { PostListItemViewModel() }
    viewModel { PostHeaderViewModel() }

    single { PlaybookService() }
    single { PostsRepository() }

    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    // Background execution threads used by retrofit
    single<ExecutorService> { BackgroundThreads() }
    // Common retrofit
    single {
        Moshi.Builder()
            .build()
    }

    factory<OkHttpClient> { (auth: Boolean, baseUrl: String) ->
        val dispatcher = Dispatcher()
        // We perform all auth requests on one thread
        if (!auth) {
            dispatcher.maxRequests = 3
        }
        OkHttpClient.Builder().apply {
            this.addInterceptor { chain ->
                val request = chain.request()
                // setting the baseURl to the current URL
                val host = HttpUrl.parse(baseUrl)?.host()
                val url = if (host != null) {
                    request.url().newBuilder()
                        .host(host)
                        .scheme(request.url().scheme())
                        .build()
                } else {
                    request.url()
                }
                val requestBuilder = request.newBuilder()
                    // set base URL from the current environment
                    .url(url)
                chain.proceed(requestBuilder.build())
            }
            this.addInterceptor(get())
            this.dispatcher(dispatcher)
        }
            .connectTimeout(apiConnectTimeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(apiWriteTimeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(apiReadTimeoutSeconds, TimeUnit.SECONDS)
            .build()
    }
    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.BODY
            } else {
                this.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single<Retrofit>("Default") {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get { parametersOf(false, baseUrl) })
            .callbackExecutor(get<ExecutorService>())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(get())
            .build()
    }
}