package com.daniyalirfan.kotlinbasewithcorutine.di.module

import android.content.Context
import com.daniyalirfan.kotlinbasewithcorutine.BuildConfig
import com.daniyalirfan.kotlinbasewithcorutine.constants.AppConstants
import com.daniyalirfan.kotlinbasewithcorutine.data.local.db.AppDao
import com.daniyalirfan.kotlinbasewithcorutine.data.local.db.AppDatabase
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.ApiService
import com.daniyalirfan.kotlinbasewithcorutine.data.repository.NoteRepositoryImpl
import com.daniyalirfan.kotlinbasewithcorutine.domain.repository.NoteRepository
import com.daniyalirfan.kotlinbasewithcorutine.domain.usecases.NoteUseCase
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient()= if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.ApiConfiguration.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.appDao()

    @Singleton
    @Provides
    fun provideNoteUseCase(noteRepository: NoteRepositoryImpl,networkHelper: NetworkHelper) = NoteUseCase(noteRepository,networkHelper)

}