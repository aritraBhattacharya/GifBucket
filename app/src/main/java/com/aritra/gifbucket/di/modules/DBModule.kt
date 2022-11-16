package com.aritra.gifbucket.di.modules

import android.content.Context
import com.aritra.gifbucket.data.local.db.GifDataBase
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun provideGifDB(appContext: Context) = GifDataBase.getDatabase(appContext)

    @Provides
    fun provideGifDao(gifDataBase: GifDataBase) = gifDataBase.getGifDao()


}