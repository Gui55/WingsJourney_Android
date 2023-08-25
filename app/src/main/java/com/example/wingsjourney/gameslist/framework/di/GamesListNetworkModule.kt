package com.example.wingsjourney.gameslist.framework.di

import com.example.wingsjourney.gameslist.framework.di.qualifier.BaseUrl
import com.example.wingsjourney.gameslist.framework.network.GamesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object GamesListNetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
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
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String
    ) : GamesApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(GamesApi::class.java)
    }
}