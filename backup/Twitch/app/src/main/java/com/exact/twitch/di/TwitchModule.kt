package com.exact.twitch.di

import android.os.AsyncTask
import com.exact.twitch.api.ApiService
import com.exact.twitch.api.IdApi
import com.exact.twitch.api.KrakenApi
import com.exact.twitch.api.MiscApi
import com.exact.twitch.api.UsherApi
import com.exact.twitch.model.chat.SubscriberBadgeDeserializer
import com.exact.twitch.model.chat.SubscriberBadgesResponse
import com.exact.twitch.model.user.UserEmotesDeserializer
import com.exact.twitch.model.user.UserEmotesResponse
import com.exact.twitch.repository.KrakenRepository
import com.exact.twitch.repository.TwitchService
import com.exact.twitch.util.TwitchApiHelper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class TwitchModule {

    @Singleton
    @Provides
    fun providesTwitchService(repository: KrakenRepository): TwitchService {
        return repository
    }

    @Singleton
    @Provides
    fun providesKrakenApi(@Named("okHttpWithClientId") client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJavaAdapterFactory: RxJava2CallAdapterFactory): KrakenApi {
        return Retrofit.Builder()
                .baseUrl("https://api.twitch.tv/kraken/")
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaAdapterFactory)
                .build()
                .create(KrakenApi::class.java)
    }

    @Singleton
    @Provides
    fun providesApiService(@Named("okHttpWithClientId") client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJavaAdapterFactory: RxJava2CallAdapterFactory): ApiService {
        return Retrofit.Builder()
                .baseUrl("https://api.twitch.tv/api/")
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaAdapterFactory)
                .build()
                .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesUsherApi(@Named("okHttpWithClientId") client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJavaAdapterFactory: RxJava2CallAdapterFactory): UsherApi {
        return Retrofit.Builder()
                .baseUrl("https://usher.ttvnw.net/")
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaAdapterFactory)
                .build()
                .create(UsherApi::class.java)
    }

    @Singleton
    @Provides
    fun providesMiscApi(@Named("okHttpDefault") client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJavaAdapterFactory: RxJava2CallAdapterFactory): MiscApi {
        return Retrofit.Builder()
                .baseUrl("https://api.twitch.tv/") //placeholder url
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaAdapterFactory)
                .build()
                .create(MiscApi::class.java)
    }

    @Singleton
    @Provides
    fun providesIdApi(@Named("okHttpDefault") client: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJavaAdapterFactory: RxJava2CallAdapterFactory): IdApi {
        return Retrofit.Builder()
                .baseUrl("https://id.twitch.tv/oauth2/")
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaAdapterFactory)
                .build()
                .create(IdApi::class.java)
    }

    @Singleton
    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(SubscriberBadgesResponse::class.java, SubscriberBadgeDeserializer())
                .registerTypeAdapter(UserEmotesResponse::class.java, UserEmotesDeserializer())
                .create())
    }

    @Singleton
    @Provides
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Singleton
    @Provides
    @Named("okHttpDefault")
    fun providesDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Singleton
    @Provides
    @Named("okHttpWithClientId")
    fun providesOkHttpClientWithClientId(@Named("okHttpDefault") okHttpClient: OkHttpClient): OkHttpClient {
        return okHttpClient.newBuilder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Client-ID", TwitchApiHelper.clientId)
                    .build()
            chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun providesExecutor(): Executor {
        return AsyncTask.THREAD_POOL_EXECUTOR
    }
}
