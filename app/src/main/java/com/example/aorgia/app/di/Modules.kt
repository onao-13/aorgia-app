package com.example.aorgia.app.di

import android.content.Context
import androidx.room.Room
import com.example.aorgia.api.AuthApi
import com.example.aorgia.api.ProfileApi
import com.example.aorgia.app.network.Server
import com.example.aorgia.database.ProfileDatabase
import com.example.aorgia.database.dao.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Singleton
    @Provides
    fun Retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Server.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Api
    @Singleton
    @Provides
    fun AuthApi(): AuthApi = Retrofit().create(AuthApi::class.java)

    @Singleton
    @Provides
    fun ProfileApi(): ProfileApi = Retrofit().create(ProfileApi::class.java)

    //Database
    @Singleton
    @Provides
    fun ProfileDao(profileDatabase: ProfileDatabase): ProfileDao
        = profileDatabase.profileDao()

    @Singleton
    @Provides
    fun ProfileDatabase(@ApplicationContext context: Context): ProfileDatabase
        = Room.databaseBuilder(
            context,
            ProfileDatabase::class.java,
            "profiles"
        )
        .fallbackToDestructiveMigration()
        .build()

}