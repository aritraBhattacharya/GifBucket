package com.aritra.gifbucket.di.modules

import com.aritra.gifbucket.data.remote.network.GifAPI
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
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

//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        if (BuildConfig.DEBUG) {
//            logging.level = HttpLoggingInterceptor.Level.HEADERS
//            logging.level = HttpLoggingInterceptor.Level.BODY
//        }

//        clientBuilder.cache() --> cache file
//        client.addInterceptor(HeaderInterceptor()) --> for adding header values
//        clientBuilder.addInterceptor(httpLoggingInterceptor)  --> add something like logging interceptor : HttpLoggingInterceptor
//        clientBuilder.addNetworkInterceptor() --> add the online interceptor for cache here
//        clientBuilder.addInterceptor() --> add offline interceptor for cache here
//        clientBuilder.authenticator() --> for access and refresh token
//        clientBuilder.certificatePinner() --> for ssl certificate pinning
//        clientBuilder.addInterceptor(GzipRequestInterceptor()) --> for data compression over internet
//        clientBuilder.connectTimeout()
//        clientBuilder.readTimeout(60, TimeUnit.SECOND)
//        clientBuilder.writeTimeout(60, TimeUnit.SECOND)
//        clientBuilder.connectionPool(60, TimeUnit.SECOND)
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