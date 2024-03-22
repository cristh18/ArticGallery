package com.tolodev.artic_gallery.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
//import com.tolodev.artic_gallery.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object ArticGalleryAppModule {

    private const val TIME_OUT = 20

    @Provides
    @ArticGalleryBaseUrl
    fun getBaseUrl(): String {
        return "https://api.artic.edu/api/v1"
    }

    @Provides
    @Singleton
    @ArticGalleryServices
    fun retrofitAuthenticator(
        @ArticGalleryHttpClientBuilder
        builder: OkHttpClient.Builder,
        @ArticGallerySerializer
        moshi: Moshi,
        @ArticGalleryBaseUrl
        baseUrl: String
    ): Retrofit {
        return getRetrofitBuilder(
            builder.build(),
            baseUrl,
            moshi
        ).build()
    }

    @Provides
    @Singleton
    @ArticGalleryHttpClientBuilder
    fun getAuthorizationHttpClientBuilder(
        @ArticGalleryInterceptors
        interceptor: Interceptor
    ): OkHttpClient.Builder {
        return getHttpClientBuilder(interceptor)
    }

    @Provides
    @Singleton
    @ArticGalleryInterceptors
    fun provideDynamicHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val headers = Headers.Builder()
            val newBuilder = chain.request()
                .newBuilder()
                .headers(headers.build())
                .method(chain.request().method, chain.request().body)
            chain.proceed(newBuilder.build())
        }
    }

    @Provides
    @Singleton
    @ArticGallerySerializer
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideShortLinkDatabase(@ApplicationContext appContext: Context): ShortLinkDatabase =
//        Room.databaseBuilder(
//            appContext,
//            ShortLinkDatabase::class.java, "shortly-app-db"
//        ).build()
//
//    @Provides
//    @Singleton
//    fun provideShortLinkService(@ShortlyServices retrofit: Retrofit): ShortCodeService =
//        retrofit.create(ShortCodeService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideShorteningLinkRemoteDataSource(shortCodeService: ShortCodeService): RemoteDataSource =
//        ShortLinkServerDataSource(shortCodeService)
//
//    @Provides
//    @Singleton
//    fun provideShorteningLinkLocalDataSource(shortLinkDatabase: ShortLinkDatabase): LocalDataSource =
//        ShortLinkDBDataSource(shortLinkDatabase)

    private fun getRetrofitBuilder(
        httpClient: OkHttpClient,
        url: String,
        moshi: Moshi
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    private fun getHttpClientBuilder(
        vararg interceptor: Interceptor
    ): OkHttpClient.Builder {

        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

        interceptor.forEach { clientBuilder.addInterceptor(it) }

//        if (BuildConfig.DEBUG) {

        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        clientBuilder.addInterceptor(logging)
//        }

        return clientBuilder
    }
}
