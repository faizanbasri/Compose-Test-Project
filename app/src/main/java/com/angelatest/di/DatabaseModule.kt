package com.angelatest.di

import android.content.Context
import androidx.room.Room
import com.angelatest.data.local.dao.home.ProblemsDao
import com.angelatest.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "angela_test_app_db"
        ).build()
    }

    @Provides
    fun provideProblemsDao(database: AppDatabase): ProblemsDao {
        return database.problemsDao()
    }
}