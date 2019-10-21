package io.rendecano.moneytreelight.common.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppBindingModule {

    @Provides
    @Singleton
    internal fun provideGson() = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

//    @Provides
//    @Singleton
//    internal fun provideDatabase(context: Context): AppDatabase =
//            Room.databaseBuilder(context, AppDatabase::class.java, "io.rendecano.moneytree.db")
//                    .fallbackToDestructiveMigration()
//                    .build()
}