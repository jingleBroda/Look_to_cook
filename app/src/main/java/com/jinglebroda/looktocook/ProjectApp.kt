package com.jinglebroda.looktocook

import com.jinglebroda.looktocook.di.daggerComponent.DaggerDiComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ProjectApp:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerDiComponent.builder().bindContext(this).build()
}