package com.angelatest.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.angelatest.data.local.dao.home.ProblemsDao
import com.angelatest.data.model.home.medicineResponse.Problems
import com.angelatest.utils.Converters

@Database(entities = [Problems::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun problemsDao(): ProblemsDao
}