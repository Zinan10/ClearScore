package com.app.clearscore.di

import com.app.clearscore.core.data.network.NetworkConstants.BASE_URL
import com.app.clearscore.donut.data.network.DonutApiService
import com.app.clearscore.donut.data.network.RemoteDataSource
import com.app.clearscore.donut.data.network.RetrofitRemoteDataSource
import com.app.clearscore.donut.data.repository.DefaultDonutRepository
import com.app.clearscore.donut.domain.DonutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Modules {
    @Provides
    @Singleton
    fun okHttp(): OkHttpClient {
        val tlsSpecs = listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)
        return OkHttpClient.Builder()
            .connectionSpecs(tlsSpecs)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInterface(client: OkHttpClient): DonutApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(DonutApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        donutApiService: DonutApiService
    ): RemoteDataSource {
        return RetrofitRemoteDataSource(donutApiService)
    }

    @Provides
    @Singleton
    fun provideDonutRepository(
        remoteDataSource: RemoteDataSource
    ): DonutRepository {
        return DefaultDonutRepository(remoteDataSource)
    }

}