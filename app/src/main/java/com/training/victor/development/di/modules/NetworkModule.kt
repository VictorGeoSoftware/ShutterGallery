package com.training.victor.development.di.modules

import com.training.victor.development.BuildConfig
import com.training.victor.development.data.TokenManager
import com.training.victor.development.network.ImagesRepository
import com.training.victor.development.network.LoginRepository
import com.training.victor.development.utils.myTrace
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class NetworkModule {
    companion object {
        const val NAME_BASE_URL = "NAME_BASE_URL"
        const val NAME_SIMPLE_AUTH_CLIENT = "NAME_SIMPLE_AUTH_CLIENT"
        const val NAME_BEARER_AUTH_CLIENT = "NAME_BEARER_AUTH_CLIENT"
        const val NAME_SIMPLE_RETROFIT = "NAME_SIMPLE_RETROFIT"
        const val NAME_BEARER_RETROFIT = "NAME_BEARER_RETROFIT"
    }


    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString():String = BuildConfig.API_URL

    @Provides
    @Singleton
    @Named(NAME_SIMPLE_AUTH_CLIENT)
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Accept", "text/html")
                .addHeader("Accept", "application/json")
                .addHeader("User-Agent", BuildConfig.APP_NAME)
                .addHeader("Authorization", Credentials.basic(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET))
                .build()

            return@addInterceptor it.proceed(request)
        }

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    @Named(NAME_BEARER_AUTH_CLIENT)
    fun provideBearerOkHttpClient(tokenManager: TokenManager): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.addInterceptor {

            myTrace("provideBearerOkHttpClient - tokenValue :: ${tokenManager.sessionToken.tokenValue}")
            val request = it.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "${tokenManager.sessionToken.tokenType} ${tokenManager.sessionToken.tokenValue}")
                .build()

            return@addInterceptor it.proceed(request)
        }

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    @Named(NAME_SIMPLE_RETROFIT)
    fun provideSimpleRetrofit(@Named(NAME_SIMPLE_AUTH_CLIENT) okHttpClient: OkHttpClient,
                              converter: Converter.Factory,
                              callAdapterFactory: RxJava2CallAdapterFactory,
                              @Named(NAME_BASE_URL) baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    @Singleton
    @Named(NAME_BEARER_RETROFIT)
    fun provideBearerRetrofit(@Named(NAME_BEARER_AUTH_CLIENT) okHttpClient: OkHttpClient,
                              converter: Converter.Factory,
                              callAdapterFactory: RxJava2CallAdapterFactory,
                              @Named(NAME_BASE_URL) baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converter)
            .build()
    }


    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------- WEB SERVICES --------------------------------------------------------------------
    @Provides
    @Singleton
    open fun provideImagesRetrofit(@Named(NAME_BEARER_RETROFIT) retrofit: Retrofit) = retrofit.create(ImagesRepository::class.java)!!

    @Provides
    @Singleton
    open fun provideLoginRetrofit(@Named(NAME_SIMPLE_RETROFIT) retrofit: Retrofit) = retrofit.create(LoginRepository::class.java)!!
}