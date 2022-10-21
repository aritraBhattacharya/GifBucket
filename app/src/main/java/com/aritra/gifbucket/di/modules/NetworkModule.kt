package com.aritra.gifbucket.di.modules

import com.aritra.gifbucket.data.remote.network.GifAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetorfitAPI(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGifApi(retrofit: Retrofit): GifAPI {
        return retrofit.create(GifAPI::class.java)
    }
}