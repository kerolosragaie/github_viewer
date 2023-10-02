package com.kerollosragaie.githubviewer.di

import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.api.RemoteServices
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source.RemoteDataSource
import com.kerollosragaie.githubviewer.features.github_repositories.data.remote.data_source.RemoteDataSourceImpl
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
object RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).connectTimeout(20, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRemoteServices(retrofit: Retrofit): RemoteServices =
        retrofit.create(RemoteServices::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(remoteServices: RemoteServices): RemoteDataSource =
        RemoteDataSourceImpl(remoteServices)
}