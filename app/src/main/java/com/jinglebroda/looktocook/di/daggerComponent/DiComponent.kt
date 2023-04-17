package com.jinglebroda.looktocook.di.daggerComponent

import com.jinglebroda.looktocook.ProjectApp
import com.jinglebroda.looktocook.di.module.DataModules
import com.jinglebroda.looktocook.di.module.DomainModules
import com.jinglebroda.looktocook.di.module.PresentationModules
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        PresentationModules::class,
        DataModules::class,
        DomainModules::class,
    ]
)
@Singleton
interface DiComponent: AndroidInjector<ProjectApp> {
    override fun inject(instance: ProjectApp)
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindContext(app:ProjectApp):Builder
        fun build():DiComponent
    }
}