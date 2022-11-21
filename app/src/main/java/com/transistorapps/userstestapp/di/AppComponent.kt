package com.transistorapps.userstestapp.di

import android.app.Application
import com.transistorapps.userstestapp.ui.details.DetailsFragmentVM
import com.transistorapps.userstestapp.ui.main.MainFragmentVM
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    val mainFragmentVM: MainFragmentVM
    val detailsFragmentVM: DetailsFragmentVM
}