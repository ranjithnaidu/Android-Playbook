package com.ranjithnaidu.android.playbook

import android.app.Application
import com.ranjithnaidu.android.playbook.di.container
import org.koin.android.ext.android.startKoin

class AndroidPlaybookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin(this, listOf(container))
    }
}