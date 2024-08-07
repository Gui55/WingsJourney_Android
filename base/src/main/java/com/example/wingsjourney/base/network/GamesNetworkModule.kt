package com.example.wingsjourney.base.network

import com.example.wingsjourney.base.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GamesNetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

//    @Provides
//    fun provideGamesApiInterface(
//        retrofit: Retrofit
//    ) : GamesRetrofitApi{
//        return retrofit.create(GamesRetrofitApi::class.java)
//    }
}