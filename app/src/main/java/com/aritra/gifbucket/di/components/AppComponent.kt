package com.aritra.gifbucket.di.components

import com.aritra.gifbucket.GifBucketApplication
import com.aritra.gifbucket.di.modules.ContainerModule
import com.aritra.gifbucket.di.modules.NetworkModule
import com.aritra.gifbucket.presentation.activities.ContainerActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ContainerModule::class])
interface AppComponent {

    fun inject(containerActivity: ContainerActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance gifBucketApp: GifBucketApplication, // bind instance as application instance is a run time value
            networkModule: NetworkModule
        ): AppComponent
    }

}